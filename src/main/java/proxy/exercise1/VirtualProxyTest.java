package proxy.exercise1;

import org.junit.jupiter.api.Test;
import util.ClassHelper;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

//DON'T CHANGE
public class VirtualProxyTest {
    @Test
    public void testLutefiskIsInterface() throws IllegalAccessException, InstantiationException {
        assertTrue(Lutefisk.class.isInterface(),"We need to convert Lutefisk into an interface, it is our 'Subject'");
    }

    @Test
    public void testScandinaviansHaveReferenceToVirtualLutefisk() throws IllegalAccessException, InstantiationException {
        Class<? extends Lutefisk> virtualLutefiskClass = findVirtualLutefiskClass();
        for (Field field : Swede.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue(virtualLutefiskClass.isInstance(field.get(new Swede())), "lutefisk field in Swede should be assigned to a VirtualLutefisk");
            }
        }
        for (Field field : Norwegian.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue(virtualLutefiskClass.isInstance(field.get(new Norwegian())), "lutefisk field in Norwegian should be assigned to a VirtualLutefisk");
            }
        }
    }

    @Test
    public void testScandinaviansHaveNonNullReferenceToVirtualLutefisk() throws IllegalAccessException, InstantiationException {
        Class<? extends Lutefisk> virtualLutefiskClass = findVirtualLutefiskClass();
        for (Field field : Swede.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue(virtualLutefiskClass.isInstance(field.get(new Swede())), "lutefisk field in Swede should be assigned to a VirtualLutefisk");
            }
        }
        for (Field field : Norwegian.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue(virtualLutefiskClass.isInstance(field.get(new Norwegian())), "lutefisk field in Norwegian should be assigned to a VirtualLutefisk");
            }
        }
    }

    @Test
    public void testRealLutefiskClassExists() throws Exception {
        try {
            Class<? extends Lutefisk> realLutefiskClass =
                    ClassHelper.getClass("RealLutefisk").asSubclass(Lutefisk.class);
            Lutefisk realLutefisk = realLutefiskClass.getConstructor().newInstance();
            realLutefisk.eat();
        } catch (ClassCastException e) {
            fail("The RealLutefisk should be derived from Lutefisk");
        } catch (ClassNotFoundException e) {
            fail("We need a RealLutefisk class that contains the original Lutefisk code");
        }
    }

    @Test
    public void testVirtualLutefiskClassExists() throws Exception {
        try {
            Class<? extends Lutefisk> virtualLutefiskClass = findVirtualLutefiskClass();
            Lutefisk virtualLutefisk = virtualLutefiskClass.getConstructor().newInstance();
            assertEquals(1, virtualLutefiskClass.getDeclaredFields().length, "Virtual Lutefisk should only have one field, the delegate");
            Field delegate = virtualLutefiskClass.getDeclaredFields()[0];
            assertTrue(delegate.getType().isAssignableFrom(Lutefisk.class), "Delegate should be of type Lutefisk");
            delegate.setAccessible(true);
            assertNull(delegate.get(virtualLutefisk), "Initially the delegate field should be null");
            virtualLutefisk.eat();
            assertNotNull(delegate.get(virtualLutefisk), "After calling eat(), the delegate field should NOT be null");
        } catch (ClassCastException e) {
            fail("The VirtualLutefisk should be derived from Lutefisk");
        }
    }

    private Class<? extends Lutefisk> findVirtualLutefiskClass() {
        try {
            return ClassHelper.getClass("VirtualLutefisk").asSubclass(Lutefisk.class);
        } catch (ClassNotFoundException e) {
            fail("We need a VirtualLutefisk that constructs the real lutefisk on demand");
            return null; // will never be called.
        }
    }
}
