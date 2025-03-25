package dao;

import java.util.List;

import entity.Reservationmodel;
import exception.ReservationException;

public interface Reservationdao {
	Reservationmodel getReservationById(int reservationId) throws ReservationException;
	List<Reservationmodel>getReservationsByCustomerId(int customerId);
	boolean createReservation(Reservationmodel reservation);
	boolean updateReservation(Reservationmodel reservation);
	boolean cancelReservation (int reservation);
	

}
