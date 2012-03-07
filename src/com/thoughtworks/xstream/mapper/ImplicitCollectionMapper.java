// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.InitializationException;
import com.thoughtworks.xstream.alias.ClassMapper;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class ImplicitCollectionMapper extends MapperWrapper
{
    private static class NamedItemType
    {

        private static boolean isEquals(Object obj, Object obj1)
        {
            boolean flag;
            if(obj == null)
            {
                if(obj1 == null)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = obj.equals(obj1);
            }
            return flag;
        }

        @Override
		public boolean equals(Object obj)
        {
            boolean flag;
            if(obj instanceof NamedItemType)
            {
                NamedItemType nameditemtype = (NamedItemType)obj;
                if(itemType.equals(nameditemtype.itemType) && isEquals(itemFieldName, nameditemtype.itemFieldName))
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        @Override
		public int hashCode()
        {
            int i = itemType.hashCode() << 7;
            if(itemFieldName != null)
                i += itemFieldName.hashCode();
            return i;
        }

        String itemFieldName;
        Class itemType;

        NamedItemType(Class class1, String s)
        {
            itemType = class1;
            itemFieldName = s;
        }
    }

    private static class ImplicitCollectionMappingImpl
        implements Mapper.ImplicitCollectionMapping
    {

        private static boolean isEquals(Object obj, Object obj1)
        {
            boolean flag;
            if(obj == null)
            {
                if(obj1 == null)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = obj.equals(obj1);
            }
            return flag;
        }

        public NamedItemType createNamedItemType()
        {
            return new NamedItemType(itemType, itemFieldName);
        }

        @Override
		public boolean equals(Object obj)
        {
            boolean flag;
            if(obj instanceof ImplicitCollectionMappingImpl)
            {
                ImplicitCollectionMappingImpl implicitcollectionmappingimpl = (ImplicitCollectionMappingImpl)obj;
                if(fieldName.equals(implicitcollectionmappingimpl.fieldName) && isEquals(itemFieldName, implicitcollectionmappingimpl.itemFieldName))
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        @Override
		public String getFieldName()
        {
            return fieldName;
        }

        @Override
		public String getItemFieldName()
        {
            return itemFieldName;
        }

        @Override
		public Class getItemType()
        {
            return itemType;
        }

        @Override
		public int hashCode()
        {
            int i = fieldName.hashCode();
            if(itemFieldName != null)
                i += itemFieldName.hashCode() << 7;
            return i;
        }

        private String fieldName;
        private String itemFieldName;
        private Class itemType;

        ImplicitCollectionMappingImpl(String s, Class class1, String s1)
        {
            fieldName = s;
            itemFieldName = s1;
            Class class2;
            if(class1 == null)
            {
                if(ImplicitCollectionMapper.class$java$lang$Object == null)
                {
                    class2 = ImplicitCollectionMapper._mthclass$("java.lang.Object");
                    ImplicitCollectionMapper.class$java$lang$Object = class2;
                } else
                {
                    class2 = ImplicitCollectionMapper.class$java$lang$Object;
                }
            } else
            {
                class2 = class1;
            }
            itemType = class2;
        }
    }

    private static class ImplicitCollectionMapperForClass
    {

        private ImplicitCollectionMappingImpl getImplicitCollectionDefByItemFieldName(String s)
        {
            ImplicitCollectionMappingImpl implicitcollectionmappingimpl;
            if(s == null)
                implicitcollectionmappingimpl = null;
            else
                implicitcollectionmappingimpl = (ImplicitCollectionMappingImpl)itemFieldNameToDef.get(s);
            return implicitcollectionmappingimpl;
        }

        public void add(ImplicitCollectionMappingImpl implicitcollectionmappingimpl)
        {
            fieldNameToDef.put(implicitcollectionmappingimpl.getFieldName(), implicitcollectionmappingimpl);
            namedItemTypeToDef.put(implicitcollectionmappingimpl.createNamedItemType(), implicitcollectionmappingimpl);
            if(implicitcollectionmappingimpl.getItemFieldName() != null)
                itemFieldNameToDef.put(implicitcollectionmappingimpl.getItemFieldName(), implicitcollectionmappingimpl);
        }

        public String getFieldNameForItemTypeAndName(Class class1, String s)
        {
            ImplicitCollectionMappingImpl implicitcollectionmappingimpl;
            Iterator iterator;
            implicitcollectionmappingimpl = null;
            iterator = namedItemTypeToDef.keySet().iterator();
_L6:
            String s1;
            NamedItemType nameditemtype;
            ImplicitCollectionMappingImpl implicitcollectionmappingimpl1;
            if(iterator.hasNext())
            {
                nameditemtype = (NamedItemType)iterator.next();
                implicitcollectionmappingimpl1 = (ImplicitCollectionMappingImpl)namedItemTypeToDef.get(nameditemtype);
                Class class2;
                if(ImplicitCollectionMapper.class$com$thoughtworks$xstream$mapper$Mapper$Null == null)
                {
                    class2 = ImplicitCollectionMapper._mthclass$("com.thoughtworks.xstream.mapper.Mapper$Null");
                    ImplicitCollectionMapper.class$com$thoughtworks$xstream$mapper$Mapper$Null = class2;
                } else
                {
                    class2 = ImplicitCollectionMapper.class$com$thoughtworks$xstream$mapper$Mapper$Null;
                }
                if(class1 != class2)
                    continue; /* Loop/switch isn't completed */
                implicitcollectionmappingimpl = implicitcollectionmappingimpl1;
            }
_L5:
            if(implicitcollectionmappingimpl != null)
                s1 = implicitcollectionmappingimpl.getFieldName();
            else
                s1 = null;
_L4:
            return s1;
            if(!nameditemtype.itemType.isAssignableFrom(class1)) goto _L2; else goto _L1
_L1:
            break MISSING_BLOCK_LABEL_116;
_L2:
            break; /* Loop/switch isn't completed */
            if(implicitcollectionmappingimpl1.getItemFieldName() == null)
                break; /* Loop/switch isn't completed */
            if(!implicitcollectionmappingimpl1.getItemFieldName().equals(s))
                break; /* Loop/switch isn't completed */
            s1 = implicitcollectionmappingimpl1.getFieldName();
            if(true) goto _L4; else goto _L3
_L3:
            implicitcollectionmappingimpl = implicitcollectionmappingimpl1;
            if(s != null) goto _L6; else goto _L5
        }

        public ImplicitCollectionMappingImpl getImplicitCollectionDefByFieldName(String s)
        {
            return (ImplicitCollectionMappingImpl)fieldNameToDef.get(s);
        }

        public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(String s)
        {
            return (Mapper.ImplicitCollectionMapping)fieldNameToDef.get(s);
        }

        public Class getItemTypeForItemFieldName(String s)
        {
            ImplicitCollectionMappingImpl implicitcollectionmappingimpl = getImplicitCollectionDefByItemFieldName(s);
            Class class1;
            if(implicitcollectionmappingimpl != null)
                class1 = implicitcollectionmappingimpl.getItemType();
            else
                class1 = null;
            return class1;
        }

        private Map fieldNameToDef;
        private Map itemFieldNameToDef;
        private Map namedItemTypeToDef;

        private ImplicitCollectionMapperForClass()
        {
            namedItemTypeToDef = new HashMap();
            itemFieldNameToDef = new HashMap();
            fieldNameToDef = new HashMap();
        }

    }


    public ImplicitCollectionMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public ImplicitCollectionMapper(Mapper mapper)
    {
        super(mapper);
        classNameToMapper = new HashMap();
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    private ImplicitCollectionMapperForClass getMapper(Class class1)
    {
_L3:
        ImplicitCollectionMapperForClass implicitcollectionmapperforclass1;
        if(class1 == null)
            break MISSING_BLOCK_LABEL_34;
        implicitcollectionmapperforclass1 = (ImplicitCollectionMapperForClass)classNameToMapper.get(class1);
        if(implicitcollectionmapperforclass1 == null) goto _L2; else goto _L1
_L1:
        ImplicitCollectionMapperForClass implicitcollectionmapperforclass = implicitcollectionmapperforclass1;
_L4:
        return implicitcollectionmapperforclass;
_L2:
        class1 = class1.getSuperclass();
          goto _L3
        implicitcollectionmapperforclass = null;
          goto _L4
    }

    private ImplicitCollectionMapperForClass getOrCreateMapper(Class class1)
    {
        ImplicitCollectionMapperForClass implicitcollectionmapperforclass = getMapper(class1);
        if(implicitcollectionmapperforclass == null)
        {
            implicitcollectionmapperforclass = new ImplicitCollectionMapperForClass();
            classNameToMapper.put(class1, implicitcollectionmapperforclass);
        }
        return implicitcollectionmapperforclass;
    }

    public void add(Class class1, String s, Class class2)
    {
        add(class1, s, null, class2);
    }

    public void add(Class class1, String s, String s1, Class class2)
    {
        Field field = null;
_L2:
        Class class3;
        Field field1;
        if(class$java$lang$Object == null)
        {
            class3 = _mthclass$("java.lang.Object");
            class$java$lang$Object = class3;
        } else
        {
            class3 = class$java$lang$Object;
        }
        if(class1 == class3)
            break MISSING_BLOCK_LABEL_38;
        field1 = class1.getDeclaredField(s);
        field = field1;
        if(field == null)
            throw new InitializationException("No field \"" + s + "\" for implicit collection");
        break; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        throw new InitializationException("Access denied for field with implicit collection", securityexception);
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        class1 = class1.getSuperclass();
        if(true) goto _L2; else goto _L1
_L1:
        Class class4;
        if(class$java$util$Collection == null)
        {
            class4 = _mthclass$("java.util.Collection");
            class$java$util$Collection = class4;
        } else
        {
            class4 = class$java$util$Collection;
        }
        if(!class4.isAssignableFrom(field.getType()))
        {
            throw new InitializationException("Field \"" + s + "\" declares no collection");
        } else
        {
            getOrCreateMapper(class1).add(new ImplicitCollectionMappingImpl(s, class2, s1));
            return;
        }
    }

    @Override
	public String getFieldNameForItemTypeAndName(Class class1, Class class2, String s)
    {
        ImplicitCollectionMapperForClass implicitcollectionmapperforclass = getMapper(class1);
        String s1;
        if(implicitcollectionmapperforclass != null)
            s1 = implicitcollectionmapperforclass.getFieldNameForItemTypeAndName(class2, s);
        else
            s1 = null;
        return s1;
    }

    @Override
	public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class class1, String s)
    {
        ImplicitCollectionMapperForClass implicitcollectionmapperforclass = getMapper(class1);
        Mapper.ImplicitCollectionMapping implicitcollectionmapping;
        if(implicitcollectionmapperforclass != null)
            implicitcollectionmapping = implicitcollectionmapperforclass.getImplicitCollectionDefForFieldName(s);
        else
            implicitcollectionmapping = null;
        return implicitcollectionmapping;
    }

    @Override
	public Class getItemTypeForItemFieldName(Class class1, String s)
    {
        ImplicitCollectionMapperForClass implicitcollectionmapperforclass = getMapper(class1);
        Class class2;
        if(implicitcollectionmapperforclass != null)
            class2 = implicitcollectionmapperforclass.getItemTypeForItemFieldName(s);
        else
            class2 = null;
        return class2;
    }

    static Class class$com$thoughtworks$xstream$mapper$Mapper$Null;
    static Class class$java$lang$Object;
    static Class class$java$util$Collection;
    private final Map classNameToMapper;
}
