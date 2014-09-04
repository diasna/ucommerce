package com.fursel.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Field MatchValidator.
 *
 * @author jborda <jborda@monits.com>
 * @copyright 2010 Monits
 * @license Apache 2.0 License
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class FieldMatchValidator implements
		ConstraintValidator<FieldMatch, Object> {
	private String firstFieldName;
	private String secondFieldName;
	private FieldMatchType type;
	private String errorMessage;

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		type = constraintAnnotation.type();
		errorMessage = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		try {
			final Object firstObj = PropertyUtils.getProperty(value, firstFieldName);
			final Object secondObj = PropertyUtils.getProperty(value, secondFieldName);
			boolean valid = type.isValid(firstObj, secondObj);
			if (!valid) {
                context.buildConstraintViolationWithTemplate(errorMessage).addPropertyNode(secondFieldName).addConstraintViolation();
            }
			return valid;
		} catch (final Exception ignore) {
			return false;
		}
	}
}