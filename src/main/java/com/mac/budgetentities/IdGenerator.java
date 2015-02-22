/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetentities;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public interface IdGenerator extends Normalizable {

    public default void generateId(Field idField, String... values)
            throws IllegalArgumentException, IllegalAccessException {
        if (Objects.nonNull(idField) && Objects.nonNull(values) && values.length > 0) {
            Class<?> type = idField.getType();
            if (type == String.class) {
                HashFunction hf = Hashing.md5();
                Hasher hasher = hf.newHasher();
                for (String str : values) {
                    if(Objects.isNull(str) || str.isEmpty()){
                        return;
                    }
                    hasher.putString(str, Charsets.UTF_8);
                }
                HashCode hc = hasher.hash();
                String hash = hc.toString();
                if(Objects.isNull(hash)){
                    return;
                }
                idField.setAccessible(true);
                idField.set(this, hash);
                idField.setAccessible(false);
            }
        }
    }
    
    public void generateId();

    public String getGeneratedId();
}
