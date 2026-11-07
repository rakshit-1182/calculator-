const display = document.getElementById("display");

const buttons = document.querySelectorAll(".buttons button");

let firstnumber="";
let operator="";
let secondnumber="";

buttons.forEach(function(button) {
    button.addEventListener("click",function(){
        const value = button.textContent;

        if (value === "historyBtn"){
            return;
        }

        if (value === "+" || value === "-" || value === "*" || value === "/") {

            firstnumber = Number(display.value);

            operator = value;

            display.value = "";

        }

        else if (value === "=") {
            secondnumber = Number(display.value);

            let result;

            if(operator === "+"){
                result = firstnumber + secondnumber;
            }
            else if(operator === "-"){
                result = firstnumber - secondnumber;
            }
            else if(operator === "*"){
                result = firstnumber * secondnumber;
            }
            else if(operator === "/"){
                result = firstnumber / secondnumber;
            }

            display.value=result;

            console.log("Sending data:", {
                firstNumber: firstnumber,
                operator: operator,
                secondNumber: secondnumber,
                result: result
            });

            fetch("/api/calculations", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    firstNumber: firstnumber,
                    operator: operator,
                    secondNumber: secondnumber,
                    result: result
                })

            })
                .then(response => response.json())
                .then(data => {
                    console.log("calculation saved:",data);
                })
                .catch(error => {
                    console.log("Error saving calculation:",error);
                });

        }

        else if(value==="AC"){
            display.value="0";
            firstnumber="";
            operator="";
            secondnumber="";
        }

        else if (value=== "DEL") {
            display.value = display.value.slice(0,-1);
            if(display.value === ""){
                display.value="0";
            }
        }
        else {

            if (value === "." && display.value.includes(".")) {
                return;
            }

            if (display.value === "0") {
                display.value = value;
            }
            else {
                display.value+=value;
            }
        }
    });
});


const historyBtn = document.getElementById("historyBtn");
const historyDiv = document.getElementById("history");

historyBtn.addEventListener("click", function() {

    fetch("/api/calculations")
        .then(response => response.json())
        .then(data => {

            historyDiv.innerHTML = "";

            data.forEach(function(calculation) {

                const item = document.createElement("p");

                item.textContent =
                    calculation.firstNumber + " " +
                    calculation.operator + " " +
                    calculation.secondNumber + " = " +
                    calculation.result;

                historyDiv.appendChild(item);
            });

        })
        .catch(error => {
            console.log("History error:", error);
        });

});

const clearHistoryBtn = document.getElementById("clearHistoryBtn");

clearHistoryBtn.addEventListener("click", function() {
    fetch("/api/calculations", {
        method: "DELETE"
    })
        .then(response => {
            if(response.ok) {
                historyDiv.innerHTML = "<p> No recent history </p>";
                console.log("History cleared!");
            } else {
                console.error("Failed to clear history from server.");
            }
        })
        .catch(error => {
            console.error("Error clearing history:", error);
        });
});
