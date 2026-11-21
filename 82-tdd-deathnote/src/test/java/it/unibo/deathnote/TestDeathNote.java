package it.unibo.deathnote;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.impl.DeathNodeImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 

class TestDeathNote {

    //point 1
   @Test
    void testInvalidRules() {
    final DeathNote note = new DeathNodeImpl();
    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
    note.getRule(0); 
    });
    final IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
    note.getRule(-1); 
    });
    assertNotNull(exception.getMessage()); 
    assertFalse(exception.getMessage().isBlank()); 
    assertNotNull(exception1.getMessage()); 
    assertFalse(exception1.getMessage().isBlank()); 
}



    // point 2
    @Test
    void checkRules(){
         final DeathNote note = new DeathNodeImpl(); 
         for(int i = 1; i <= DeathNote.RULES.size(); i++){
           final String regola = note.getRule(i);
            assertNotNull(regola);
            assertFalse(regola.isBlank());
         }
    }

    //point 3
    @Test 
    void testNameWritten() {
        final DeathNote note = new DeathNodeImpl(); 
        // point 3
        assertFalse(note.isNameWritten("Matteo"));
        note.writeName("Matteo");
        assertTrue(note.isNameWritten("Matteo"));
        assertFalse(note.isNameWritten("Luigi"));
        assertFalse(note.isNameWritten(""));
    }

    // point 4
    @Test 
    void checkCause() throws InterruptedException {
    final DeathNote note = new DeathNodeImpl(); 
    final IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
    note.writeDeathCause("die");
    });
    assertNotNull(exception.getMessage());
    assertFalse(exception.getMessage().isBlank());
    note.writeName("giorgio");
    assertEquals("heart attack", note.getDeathCause("giorgio"));
    note.writeName("cabal");
    final boolean update = note.writeDeathCause("karting accident");
    assertTrue(update); 
    assertEquals("karting accident", note.getDeathCause("cabal")); 
    Thread.sleep(100); 
    final boolean uptadeinDelete = note.writeDeathCause("school accident");
    assertFalse(uptadeinDelete); 
    assertEquals("karting accident", note.getDeathCause("cabal"));
}

   // point 5
@Test
void checkDetails() throws InterruptedException {
    final DeathNote note = new DeathNodeImpl();
    final IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
        note.writeDetails("instant");
    });
    assertNotNull(exception.getMessage());
    assertFalse(exception.getMessage().isBlank());
    note.writeName("lukaku");
    assertEquals("", note.getDeathDetails("lukaku"));
    assertTrue(note.writeDetails("ran for too long"));
    assertEquals("ran for too long", note.getDeathDetails("lukaku"));
    note.writeName("icardi");
    Thread.sleep(6100);
    assertFalse(note.writeDetails("drink vodka"));
    assertEquals("", note.getDeathDetails("icardi"));
}
}