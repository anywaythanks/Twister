package com.github.anywaythanks.twisterresource.configs;

import com.github.anywaythanks.twisterresource.mappers.*;
import org.mapstruct.MapperConfig;
import org.springframework.cache.CacheManager;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_NULL;
import static org.mapstruct.ReportingPolicy.ERROR;
import static org.mapstruct.SubclassExhaustiveStrategy.COMPILE_ERROR;

@MapperConfig(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        nullValueCheckStrategy = ALWAYS,
        nullValueMappingStrategy = RETURN_NULL,
        unmappedTargetPolicy = ERROR,
        subclassExhaustiveStrategy = COMPILE_ERROR,

        uses = {AccountMapper.class, CacheManager.class, GeneralAccountMapper.class,
                InventoryMapper.class, InventoryMapper.class, MoneyMapper.class,
                MoneyTypeMapper.class, PageMapper.class, SlotMapper.class, ItemMapper.class,
                TwistMapper.class, TwistMarkMapper.class})
public interface MapstructConfig {
}
