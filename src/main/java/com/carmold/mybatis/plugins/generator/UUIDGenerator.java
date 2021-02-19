package com.carmold.mybatis.plugins.generator;


import java.io.Serializable;


public class UUIDGenerator implements IdGenerator {

    @Override
    public Serializable generator() {
        return new ObjectId().toString();
    }

}
