const validateAfter3Chars = handler => value => value.length >= 3 && handler();
const checkers = {
    validateStringLength: string => Boolean(string.length),
    validateByRegExp: (regExp, value) => regExp.test(value)
};

function handleUserNameBlur(event) {
    validateInputName(event.target);
}

function handleUserEmailInput(event) {
    const validateUserEmailAfter3Chars = validateAfter3Chars(() => {
        validateInputEmail(event.target);
    });
    validateUserEmailAfter3Chars(event.target.value);
}

function handleUserPasswordBlur(event) {
    validateInputPassword(event.target);
}
function handleRegistrationFormSubmit(event) {
    const isValid = validateRegistrationForm(event.target);

    if (!isValid) {
        event.preventDefault();
    }
}