package memento.exercise2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

//DON'T CHANGE
public class MementoTest {
    @Test
    public void testClassStructure() throws ReflectiveOperationException {
        assertTrue(Serializable.class.isAssignableFrom(Employee.class), "Employee needs to be Serializable");

        try {
            Method writeReplace = Employee.class.getDeclaredMethod("writeReplace");
            assertEquals(Object.class, writeReplace.getReturnType(), "writeReplace() return type should be Object");
            assertTrue(Modifier.isPrivate(writeReplace.getModifiers()), "writeReplace() should be private");
        } catch (NoSuchMethodException e) {
            fail("Employee class needs a writeReplace() method to return the MementoImpl");
        }
        boolean foundMementoImpl = false;
        for (Class<?> clazz : Employee.class.getDeclaredClasses()) {
            if (clazz.getSimpleName().equals("MementoImpl")) {
                assertTrue(Serializable.class.isAssignableFrom(clazz), "Employee.MementoImpl needs to be Serializable");
                try {
                    Method readResolve = clazz.getDeclaredMethod("readResolve");
                    assertEquals(Object.class, readResolve.getReturnType(), "readResolve() return type should be Object");
                    assertTrue(Modifier.isPrivate(readResolve.getModifiers()), "readResolve() should be private");
                } catch (NoSuchMethodException e) {
                    fail("Employee.MementoImpl needs a readResolve() method to convert back to an Employee");
                }
                foundMementoImpl = true;
                break;
            }
        }
        if (!foundMementoImpl) fail("Could not find MementoImpl inner class");
    }

    @Test
    public void testSerialization() throws Exception {
        Employee[] employees = {
                new Employee(), new Employee(), new Employee()
        };
        employees[0].promote();
        employees[0].promote();
        employees[0].promote();
        employees[1].pay();
        employees[1].pay();
        employees[1].pay();
        employees[2].promote();
        employees[2].promote();
        employees[2].promote();
        employees[2].pay();
        employees[2].pay();
        employees[2].pay();

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
            for (Employee employee : employees) {
                out.writeObject(employee);
            }
        }
        byte[] serializedEmployees = bout.toByteArray();
        String serializedEmployeesAsString = new String(serializedEmployees);
        System.out.println("Serialized form: " + serializedEmployeesAsString);
        assertTrue(serializedEmployeesAsString.contains("Employee$MementoImpl"), "writeReplace() needs to return the MementoImpl");

        try (ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(serializedEmployees)
        )) {
            for (int i = 0; i < employees.length; i++) {
                assertEquals(employees[i], in.readObject(), "Serialization mismatch for employee " + i);
            }
        }
    }
}