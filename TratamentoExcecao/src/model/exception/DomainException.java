package model.exception;

public class DomainException extends RuntimeException{
	private static final long serialVersionUID = 1L; // declarando vers�o de um serializable (DomainException)
	
	public DomainException(String msg) {    // pra permitir que eu possa instanciar minha exce��o personalizada (DomainException) com uma mensagem (msg)
		super(msg);
		
	}
	
}
