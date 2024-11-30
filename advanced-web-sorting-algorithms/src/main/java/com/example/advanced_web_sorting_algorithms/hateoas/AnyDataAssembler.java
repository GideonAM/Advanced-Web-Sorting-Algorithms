package com.example.advanced_web_sorting_algorithms.hateoas;

import com.example.advanced_web_sorting_algorithms.entity.AnyData;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

@Service
public class AnyDataAssembler implements RepresentationModelAssembler<AnyData, EntityModel<AnyData>> {

    @Override
    public EntityModel<AnyData> toModel(AnyData entity) {
        EntityModel<AnyData> dataEntityModel = EntityModel.of(entity);
        dataEntityModel.add(Link.of("http://localhost:8080/api/any-data").withRel("all any-data"));

        return dataEntityModel;
    }
}
