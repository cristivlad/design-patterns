package proxy.exercise2;

import org.junit.jupiter.api.Test;
import util.ClassHelper;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

//DON'T CHANGE
public class VirtualProxyTest {
    @Test
    public void testOldHandWrittenVirtualLutefiskIsGone() throws Exception {
        try {
            ClassHelper.getClass("VirtualLutefisk");
            fail("Old hand-crafted VirtualLutefisk should be gone");
        } catch (ClassNotFoundException ignored) {}
    }

    @Test
    public void testDynamicVirtualLutefiskClassExists() throws Exception {
        try {
            findVirtualLutefiskClass();
        } catch (ClassCastException e) {
            fail("The VirtualLutefisk should be derived from Lutefisk");
        }
    }

    private Class<? extends Lutefisk> findVirtualLutefiskClass() {
        try {
            Swede swede = new Swede();
            for (Field field : Swede.class.getDeclaredFields()) {
                if (field.getType() == Lutefisk.class) {
                    field.setAccessible(true);
                    Lutefisk lutefisk = (Lutefisk) field.get(swede);
                    assertNotSame(lutefisk, Lutefisk.class, "No virtual lutefisk subclass found");
                    assertTrue(lutefisk.getClass().getName().contains(".$Proxy"), "Virtual lutefisk is not a dynamic proxy");
                    return lutefisk.getClass();
                }
            }
            fail("Lutefisk type of field missing in Swede");
            return null;
        } catch (ReflectiveOperationException ex) {
            fail("Could not find virtual lutefisk field in Swede: " + ex);
            return null; // will never be called
        }
    }
}
