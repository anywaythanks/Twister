package com.github.anywaythanks.twisterresource.generators;

import com.github.anywaythanks.twisterresource.exceptions.GeneratorException;
import jakarta.persistence.Id;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class StringPrefixedSequenceIdGenerator extends SequenceStyleGenerator {
    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String VALUE_PREFIX_DEFAULT = "";
    private String valuePrefix;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%d";
    private String numberFormat;

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object object) throws HibernateException {
        Field[] fields = object.getClass().getDeclaredFields();
        Object id = null;
        for (var field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                try {
                    field.setAccessible(true);
                    id = field.get(object);
                } catch (IllegalAccessException e) {
                    throw new GeneratorException(e);
                }
            }
        }
        if (id == null) {
            var methods = object.getClass().getMethods();
            for (var method : methods) {
                if (method.isAnnotationPresent(Id.class)) {
                    try {
                        method.setAccessible(true);
                        id = method.invoke(object);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new GeneratorException(e);
                    }
                }
            }
        }
        if (id == null) return valuePrefix + String.format(numberFormat, super.generate(session, object));
        else return id.toString();
    }

    @Override
    public void configure(Type type, Properties params,
                          ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(Long.class), params, serviceRegistry);
        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER,
                params, VALUE_PREFIX_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER,
                params, NUMBER_FORMAT_DEFAULT);
    }
}
