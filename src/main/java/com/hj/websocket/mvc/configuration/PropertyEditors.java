package com.hj.websocket.mvc.configuration;


import org.springframework.beans.propertyeditors.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;
import org.xml.sax.InputSource;

import java.beans.PropertyEditor;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @Author: 01396614
 * @Date: 2020/8/13 15:55
 * @description: TODO
 */
public class PropertyEditors {
    public static Map<Class<?>, PropertyEditor> defaultEditors = new HashMap<>();

    static {
        defaultEditors.put(Collection.class, new CustomCollectionEditor(Collection.class));
        defaultEditors.put(Set.class, new CustomCollectionEditor(Set.class));
        defaultEditors.put(SortedSet.class, new CustomCollectionEditor(SortedSet.class));
        defaultEditors.put(List.class, new CustomCollectionEditor(List.class));
        defaultEditors.put(SortedMap.class, new CustomMapEditor(SortedMap.class));

// Default editors for primitive arrays.
        defaultEditors.put(byte[].class, new ByteArrayPropertyEditor());
        defaultEditors.put(char[].class, new CharArrayPropertyEditor());

// The JDK does not contain a default editor for char!
        defaultEditors.put(char.class, new CharacterEditor(false));
        defaultEditors.put(Character.class, new CharacterEditor(true));

// Spring's CustomBooleanEditor accepts more flag values than the JDK's default editor.
        defaultEditors.put(boolean.class, new CustomBooleanEditor(false));
        defaultEditors.put(Boolean.class, new CustomBooleanEditor(true));

// The JDK does not contain default editors for number wrapper types!
// Override JDK primitive number editors with our own CustomNumberEditor.
        defaultEditors.put(byte.class, new CustomNumberEditor(Byte.class, false));
        defaultEditors.put(Byte.class, new CustomNumberEditor(Byte.class, true));
        defaultEditors.put(short.class, new CustomNumberEditor(Short.class, false));
        defaultEditors.put(Short.class, new CustomNumberEditor(Short.class, true));
        defaultEditors.put(int.class, new CustomNumberEditor(Integer.class, false));
        defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));
        defaultEditors.put(long.class, new CustomNumberEditor(Long.class, false));
        defaultEditors.put(Long.class, new CustomNumberEditor(Long.class, true));
        defaultEditors.put(float.class, new CustomNumberEditor(Float.class, false));
        defaultEditors.put(Float.class, new CustomNumberEditor(Float.class, true));
        defaultEditors.put(double.class, new CustomNumberEditor(Double.class, false));
        defaultEditors.put(Double.class, new CustomNumberEditor(Double.class, true));
        defaultEditors.put(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
        defaultEditors.put(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));

// Only register config value editors if explicitly requested.

        StringArrayPropertyEditor sae = new StringArrayPropertyEditor();
        defaultEditors.put(String[].class, sae);
        defaultEditors.put(short[].class, sae);
        defaultEditors.put(int[].class, sae);
        defaultEditors.put(long[].class, sae);
    }
}