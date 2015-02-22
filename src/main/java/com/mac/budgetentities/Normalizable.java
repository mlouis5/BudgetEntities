/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetentities;

import com.mac.budgetentities.pojos.User;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mac
 */
public interface Normalizable {
    /**
     *
     * @param instance
     * @param declaredFields
     * @throws java.lang.IllegalAccessException
     */
    public default void normalize(Object instance, Field[] declaredFields) 
            throws IllegalArgumentException, IllegalAccessException {        
        if (Objects.nonNull(instance)) {
            Field[] fields = Optional.ofNullable(declaredFields).orElse(new Field[1]);            
            for (Field field : fields) {                
                if (Objects.nonNull(field)) {                    
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (type == String.class) {
                        String val = (String) field.get(instance);                        
                        if (Objects.nonNull(val)) {                            
                            field.set(instance, val.toUpperCase());                            
                        }
                    }
                    field.setAccessible(false);
                }
            }
        }
    }
    
    public default void print() {
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append(" ");
        
        Field[] fields = getClass().getDeclaredFields();
                
        for(Field field : fields){
            field.setAccessible(true);
            sb.append("[");
            sb.append(" ");
            sb.append(field.getName());
            sb.append("=");
            try {
                String val = String.valueOf(field.get(this));
                sb.append(val);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            sb.append(" ");
            sb.append("]");
            sb.append(" ");
            field.setAccessible(false);
        }
        sb.trimToSize();
        System.out.println(sb.toString());
    }
}
