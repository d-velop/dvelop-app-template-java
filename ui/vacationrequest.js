'use strict';

const textFields = document.querySelectorAll(".mdc-text-field");
for (let i = 0; i < textFields.length; i++) {
    mdc.textField.MDCTextField.attachTo(textFields[i]);
}

const buttons = document.querySelectorAll(".mdc-button");
for (let i = 0; i < buttons.length; i++) {
    mdc.ripple.MDCRipple.attachTo(buttons[i]);
}

const inputs = document.querySelectorAll("input, select, textarea");
const submitBtn = document.getElementById("submit");
const snackbar = mdc.snackbar.MDCSnackbar.attachTo(document.querySelector(".mdc-snackbar"));
const form = document.getElementById("form");
const fromInput = document.getElementById("from");
const toInput = document.getElementById("to");
const typeInput = document.getElementById("type");
const commentInput = document.getElementById("comment");

submitBtn.addEventListener("click", function (e) {
    const r = new XMLHttpRequest();
    r.addEventListener("load", function () {
        if (r.status === 202) {
            window.location = "./";
        } else {
            snackbar.labelText = "Request failed. Server returned " + r.status;
            snackbar.open();
        }
    });
    r.addEventListener("error", function () {
        snackbar.labelText = "Request failed. Please try again in 5 seconds.";
        snackbar.open();
    });
    r.open( "PUT", location.href);
    r.setRequestHeader("Content-Type", "application/json");
    r.send(JSON.stringify({
        from: fromInput.valueAsDate.toISOString(),
        to: toInput.valueAsDate.toISOString(),
        type: typeInput.value,
        comment: commentInput.value
    }));
    e.preventDefault();
});