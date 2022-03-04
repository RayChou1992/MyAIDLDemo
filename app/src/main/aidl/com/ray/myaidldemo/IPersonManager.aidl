// IPersonManager.aidl
package com.ray.myaidldemo;
import com.ray.myaidldemo.IPerson;//引用的是IPerson.aidl

// Declare any non-default types here with import statements

interface IPersonManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    List<Person> getPersons();
    void addPerson(in Person person);

}