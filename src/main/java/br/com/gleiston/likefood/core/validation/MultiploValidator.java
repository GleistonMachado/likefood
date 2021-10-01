package br.com.gleiston.likefood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {
	
	private int numeroValidator;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numeroValidator = constraintAnnotation.numero();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		
		boolean isValido = true;
		
		if(value != null) {
			
			var valorDecimal = BigDecimal.valueOf(value.doubleValue());  // Numero que vem da api e sera validado
			var numeroValidatorDecimal = BigDecimal.valueOf(this.numeroValidator);  // Numero que é determida na anotação
			var isMultiplo = valorDecimal.remainder(numeroValidatorDecimal); 
			
			isValido = BigDecimal.ZERO.compareTo(isMultiplo) == 0;
			 
		}
		
		return isValido;
	}

}
