// Constants './constants.js'
const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

// Utils './utils.js'

function addValidationClasses(field, isValid) {
    field.classList.toggle('is-valid', isValid);
    field.classList.toggle('is-invalid', !isValid);
}

function validateInputName(userNameInput) {
    addValidationClasses(userNameInput, checkers.validateStringLength(userNameInput.value));
}

function validateInputEmail(userEmailInput) {
    addValidationClasses(userEmailInput, checkers.validateByRegExp(EMAIL_REG_EXP, userEmailInput.value));
}

function validateInputPassword(userPasswordInput) {
    addValidationClasses(userPasswordInput, userPasswordInput.value.length >= 7);
}

function validateInputConfirmPassword(userConfirmPasswordInput, userPasswordInput) {
    addValidationClasses(userConfirmPasswordInput, userPasswordInput.value == userConfirmPasswordInput.value && $(userConfirmPasswordInput).val().length != 0);
}

function validateRegistrationForm(registrationForm) {
    const registrationFormInputs = registrationForm.querySelectorAll(':scope input');
    const registrationFormInputsArray = Array.prototype.slice.call(registrationFormInputs);
    const inputName = registrationForm.elements.userName;
    const inputEmail = registrationForm.elements.userEmail;
    const inputPassword = registrationForm.elements.userPassword;
    const inputConfirmPassword = registrationForm.elements.userConfirmPassword;


    validateInputName(inputName);
    validateInputEmail(inputEmail);
    validateInputPassword(inputPassword);
    validateInputConfirmPassword(inputConfirmPassword, inputPassword);

    return !registrationFormInputsArray.some(input => input.classList.contains('is-invalid'));
}

// Registration Form Handlers './registration-form-handlers.js'

function handleUserConfirmPasswordBlur(event) {
    const registrationForm = document.getElementById('registration-form');
    const userPasswordInput = registrationForm.elements.userPassword;
    validateInputConfirmPassword(event.target, userPasswordInput);
}

// Registration Form './registration-form.js'

const registrationForm = document.getElementById('registration-form');

function initializeRegistrationFormValidation(registrationForm) {
    const inputName = registrationForm.elements.userName;
    const inputEmail = registrationForm.elements.userEmail;
    const inputPassword = registrationForm.elements.userPassword;
    const inputConfirmPassword = registrationForm.elements.userConfirmPassword;

    registrationForm.addEventListener('submit', handleRegistrationFormSubmit);
    inputEmail.addEventListener('input', handleUserEmailInput);
    inputName.addEventListener('input', handleUserNameBlur);
    inputPassword.addEventListener('input', handleUserPasswordBlur);
    inputConfirmPassword.addEventListener('input', handleUserConfirmPasswordBlur);
}

initializeRegistrationFormValidation(registrationForm);