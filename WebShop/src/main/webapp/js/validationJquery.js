// Constants './constants.js'
const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

// Utils './utils.js'

function addValidationClasses(field, isValid) {
  $(field).toggleClass('is-valid', isValid);
  $(field).toggleClass('is-invalid', !isValid);
}

function validateInputName(userNameInput) {
  addValidationClasses(userNameInput, checkers.validateStringLength($(userNameInput).val()));
}

function validateInputEmail(userEmailInput) {
  addValidationClasses(userEmailInput, checkers.validateByRegExp(EMAIL_REG_EXP, $(userEmailInput).val()));
}

function validateInputPassword(userPasswordInput) {
  addValidationClasses(userPasswordInput, $(userPasswordInput).val().length >= 7);
}

function validateInputConfirmPassword(userConfirmPasswordInput, userPasswordInput) {
  addValidationClasses(userConfirmPasswordInput, $(userPasswordInput).val() == $(userConfirmPasswordInput).val() && $(userConfirmPasswordInput).val().length != 0);
}

function validateRegistrationForm(registrationForm) {
  const registrationFormInputs = $(registrationForm).find('input');
  const inputName = $(registrationForm).find('[name="userName"]');
  const inputEmail = $(registrationForm).find('[name="userEmail"]');
  const inputPassword = $(registrationForm).find('[name="userPassword"]');
  const inputConfirmPassword = $(registrationForm).find('[name="userConfirmPassword"]');



  validateInputName(inputName);
  validateInputEmail(inputEmail);
  validateInputPassword(inputPassword);
  validateInputConfirmPassword(inputConfirmPassword, inputPassword);

  return !registrationFormInputs.hasClass('is-invalid');
}

// Registration Form Handlers './registration-form-handlers.js'

function handleUserConfirmPasswordBlur(event) {
  const registrationForm = $('#registration-form');
  const userPasswordInput = $(registrationForm).find('[name="userPassword"]');
  validateInputConfirmPassword(event.target, userPasswordInput);
}

// Registration Form './registration-form.js'

const registrationForm = $('#registration-form');

function initializeRegistrationFormValidation(registrationForm) {
  const inputName = $(registrationForm).find('[name="userName"]');
  const inputEmail = $(registrationForm).find('[name="userEmail"]');
  const inputPassword = $(registrationForm).find('[name="userPassword"]');
  const inputConfirmPassword = $(registrationForm).find('[name="userConfirmPassword"]');

  $(registrationForm).submit(handleRegistrationFormSubmit);
  $(inputEmail).on('input', handleUserEmailInput);
  $(inputName).on('input', handleUserNameBlur);
  $(inputPassword).on('input', handleUserPasswordBlur);
  $(inputConfirmPassword).on('input', handleUserConfirmPasswordBlur);
}

initializeRegistrationFormValidation(registrationForm);