package com.springboot.ultimate.mappers;

/**
 * @author prabhakar, @Date 07-08-2024
 */
public interface Mapper<P,K> {

    K mapTo(P p);

    P mapFrom(K k);
}
