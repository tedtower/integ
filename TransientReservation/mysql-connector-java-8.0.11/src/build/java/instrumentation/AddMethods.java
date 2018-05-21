/*
 * Copyright (c) 2017, 2018, Oracle and/or its affiliates. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package instrumentation;

import java.util.Collection;

import com.mysql.cj.conf.BooleanPropertyDefinition;
import com.mysql.cj.conf.EnumPropertyDefinition;
import com.mysql.cj.conf.IntegerPropertyDefinition;
import com.mysql.cj.conf.LongPropertyDefinition;
import com.mysql.cj.conf.MemorySizePropertyDefinition;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.StringPropertyDefinition;
import com.mysql.cj.jdbc.MysqlDataSource;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class AddMethods {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(args[0]);

        System.out.println("---");
        CtClass clazz = pool.get(MysqlDataSource.class.getName());
        System.out.println("Add properties setters/getters to " + clazz.getName());
        addPropertiesGettersSetters(clazz, PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.values());
        clazz.writeFile(args[0]);

    }

    private static void addPropertiesGettersSetters(CtClass clazz, Collection<PropertyDefinition<?>> propertyDefinitions) throws Exception {
        for (PropertyDefinition<?> def : propertyDefinitions) {
            String pname = def.hasCcAlias() ? def.getCcAlias() : def.getName();

            if (def instanceof StringPropertyDefinition) {
                addGetter(clazz, pname, String.class.getName(), "getStringProperty");
                addSetter(clazz, pname, String.class.getName(), "setStringProperty");

            } else if (def instanceof BooleanPropertyDefinition) {
                addGetter(clazz, pname, Boolean.TYPE.getName(), "getBooleanProperty");
                addSetter(clazz, pname, Boolean.TYPE.getName(), "setBooleanProperty");

            } else if (def instanceof IntegerPropertyDefinition) {
                addGetter(clazz, pname, Integer.TYPE.getName(), "getIntegerProperty");
                addSetter(clazz, pname, Integer.TYPE.getName(), "setIntegerProperty");

            } else if (def instanceof LongPropertyDefinition) {
                addGetter(clazz, pname, Long.TYPE.getName(), "getLongProperty");
                addSetter(clazz, pname, Long.TYPE.getName(), "setLongProperty");

            } else if (def instanceof MemorySizePropertyDefinition) {
                addGetter(clazz, pname, Integer.TYPE.getName(), "getMemorySizeProperty");
                addSetter(clazz, pname, Integer.TYPE.getName(), "setMemorySizeProperty");

            } else if (def instanceof EnumPropertyDefinition<?>) {
                addGetter(clazz, pname, String.class.getName(), "getEnumProperty");
                addSetter(clazz, pname, "java.lang.String", "setEnumProperty");

            } else {
                throw new Exception("Unknown " + def.getName() + " property type.");
            }
        }
    }

    private static void addGetter(CtClass clazz, String pname, String paramType, String getPropertyMethod) throws Exception {
        String mname = "get" + pname.substring(0, 1).toUpperCase() + pname.substring(1);
        String mbody = "public " + paramType + " " + mname + "() throws java.sql.SQLException { return " + getPropertyMethod + "(\"" + pname + "\");}";
        System.out.println(mbody);

        CtMethod m = CtNewMethod.make(mbody, clazz);
        clazz.addMethod(m);
        System.out.println(m);
    }

    private static void addSetter(CtClass clazz, String pname, String paramType, String setPropertyMethod) throws Exception {
        String mname = "set" + pname.substring(0, 1).toUpperCase() + pname.substring(1);
        String mbody = "public void " + mname + "(" + paramType + " value) throws java.sql.SQLException { " + setPropertyMethod + "(\"" + pname
                + "\", value);}";
        System.out.println(mbody);

        CtMethod m = CtNewMethod.make(mbody, clazz);
        clazz.addMethod(m);
        System.out.println(m);
    }
}
