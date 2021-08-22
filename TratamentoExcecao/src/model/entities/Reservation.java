package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {    // "throws" propaga o m�todo quando n�o � necess�rio/ a fun��o tratar, mas como estou usando o RuntimeExcpetion isso n�o se faz necess�rio
		if (!checkOut.after(checkIn)){      // tratando as exce��es no construtor, isso se chama programa��o defensiva  (boa pr�tica)
			throw new DomainException("check-out must be after check-in date.");   // IllegalArgumentException aparece no programa principal no teste de erro
			
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();             // pega os dias do checkIn e faz o c�lculo de milesegundos para dias
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	public void UpdateDates(Date checkIn, Date checkOut) {	// "throws" permite que eu possa lan�ar uma exce��o, quando n�o � necess�rio/a fun��o, dessa classe, mas como estou usando o RuntimeExcpetion isso n�o se faz necess�rio
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for udpdate must be future dates.");  // usado para lan�ar o argumento no try catch 
		}
	
		this.checkIn = checkIn;
		this.checkOut = checkOut;	
	}
	
	@Override          // sobre-escreve o m�todo herdado
	public String toString() {
		return "Room "
				+ roomNumber
				+", check-In "
				+ sdf.format(checkIn)
				+ ", check-Out"
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights ";
	}
}
