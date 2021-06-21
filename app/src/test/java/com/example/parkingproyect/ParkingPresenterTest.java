package com.example.parkingproyect;

import com.example.parkingproyect.mvp.model.Parking;
import com.example.parkingproyect.mvp.presenter.ParkingPresenter;
import com.example.parkingproyect.mvp.view.ParkingView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingPresenterTest {
    private ParkingPresenter presenter;
    private Parking model;
    private ParkingView view;

    @Before
    public void setup() {
        model = mock(Parking.class);
        view = mock(ParkingView.class);
        presenter = new ParkingPresenter(model, view);
    }

    @Test
    public void isShouldAddParkingSize() {
        // Given
        when(view.getSize()).thenReturn("20");
        // When
        presenter.onSizeCreationButtonPressed();
        // Then
        verify(model).setParkingSize("20");
        verify(view).showSizeMessage("20");
    }

    @Test
    public void isShouldAddParkingSizeException() {
        // Given
        when(view.getSize()).thenReturn("100000000000");
        doThrow(new NumberFormatException()).when(model).setParkingSize("100000000000");
        // When
        presenter.onSizeCreationButtonPressed();
        // Then
        verify(view).showInvalidError();
        verify(view, never()).showSizeMessage(any());
    }

    @Test
    public void isShouldAddParkingSizeLessOrEqual0() {
        // Given
        when(view.getSize()).thenReturn("0");
        // When
        presenter.onSizeCreationButtonPressed();
        // Then
        verify(view).showErrorMessage();
    }
}