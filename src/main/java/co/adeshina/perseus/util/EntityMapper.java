package co.adeshina.perseus.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EntityMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public <T, E> E  map(T source, Class<E> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
