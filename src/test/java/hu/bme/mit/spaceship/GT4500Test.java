package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore tsp;
  TorpedoStore tss;

  @Before
  public void init(){
    tsp = mock(TorpedoStore.class);
    tss = mock(TorpedoStore.class);
    this.ship = new GT4500(tsp, tss);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange

    when(tsp.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(tsp, times(1)).fire(1);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
    when(tsp.fire(1)).thenReturn(true);
    when(tss.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_Both_Success(){

    when(tsp.fire(1)).thenReturn(true);
    when(tss.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedos_FristTwice_Success(){

    when(tsp.fire(1)).thenReturn(true);
    when(tss.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedos(FiringMode.SINGLE);
    boolean result3 = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    assertEquals(true, result3);
  }


  @Test
  public void fireTorpedos_EmptyPrimaryStore_Success(){
    when(tsp.fire(1)).thenReturn(false);
    when(tss.fire(1)).thenReturn(true);
    when(tsp.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_EmptySecoundaryStore_Success(){
    when(tsp.fire(1)).thenReturn(true);
    when(tss.fire(1)).thenReturn(false);
    when(tss.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedos(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedos_Both_Fail(){

    when(tsp.fire(1)).thenReturn(false);
    when(tss.fire(1)).thenReturn(false);
    when(tss.isEmpty()).thenReturn(true);
    when(tsp.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);
    boolean result2 = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    assertEquals(false, result2);
  }

  @Test
  public void fireTorpedos_OnlyPrimaryCanFire_Success() {
    when(tsp.fire(1)).thenReturn(true);
    when(tss.fire(1)).thenReturn(false);
    when(tss.isEmpty()).thenReturn(true);

    // Act
    assertEquals(ship.fireTorpedos(FiringMode.SINGLE), true);
    assertEquals(ship.fireTorpedos(FiringMode.SINGLE), true);
    assertEquals(ship.fireTorpedos(FiringMode.SINGLE), true);
    assertEquals(ship.fireTorpedos(FiringMode.SINGLE), true);


  }



  }
