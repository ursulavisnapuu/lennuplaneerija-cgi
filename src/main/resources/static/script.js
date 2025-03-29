// Funktsioon lendude filtreerimiseks
function filterFlights() {
    const origin = document.getElementById("origin").value;
    const destination = document.getElementById("destination").value;
    const date = document.getElementById("date").value;

    fetch(`/api/flights/search?origin=${origin}&destination=${destination}&date=${date}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Midagi läks valesti!");
            }
            return response.json();
        })
        .then(data => {
            displayFlights(data);
        })
        .catch(error => {
            console.error("Viga lendude otsimisel:", error);
        });
}

// Kuvame leitud lennud
function displayFlights(flights) {
    const seatPlan = document.getElementById("seatPlan");
    seatPlan.innerHTML = "<h2>Leitud lennud:</h2>";

    if (flights.length === 0) {
        seatPlan.innerHTML += "<p>Ühtegi lendu ei leitud.</p>";
        return;
    }

    flights.forEach(flight => {
        const flightDiv = document.createElement("div");
        flightDiv.className = "flight";

        flightDiv.innerHTML = `
            <p><strong>Lähtekoht:</strong> ${flight.origin}</p>
            <p><strong>Sihtkoht:</strong> ${flight.destination}</p>
            <p><strong>Kuupäev:</strong> ${flight.date}</p>
            <p><strong>Hind:</strong> ${flight.price}€</p>
            <button onclick="recommendSeats(${flight.id})">Soovita istekohta</button>
        `;

        seatPlan.appendChild(flightDiv);
    });
}

// Funktsioon istekoha soovitamiseks
function recommendSeats(flightId) {
    const prefersWindow = document.getElementById("window").checked;
    const prefersLegroom = document.getElementById("legroom").checked;
    const prefersExit = document.getElementById("exit").checked;

    const preferences = {
        window: prefersWindow,
        legroom: prefersLegroom,
        exit: prefersExit
    };

    fetch(`/api/seats/recommend/${flightId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(preferences)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Soovitamine ebaõnnestus");
            }
            return response.json();
        })
        .then(data => {
            displayRecommendedSeats(data);
        })
        .catch(error => {
            console.error("Viga istekohtade soovitamisel:", error);
        });
}

// Kuvame soovitatud istekohad
function displayRecommendedSeats(seats) {
    const seatPlan = document.getElementById("seatPlan");
    seatPlan.innerHTML = "<h2>Soovitatud istekohad:</h2>";

    if (!Array.isArray(seats) || seats.length === 0) {
        seatPlan.innerHTML += "<p>Vabu istekohti ei leitud vastavalt soovidele.</p>";
        return;
    }

    seats.forEach(seat => {
        seatPlan.innerHTML += `
            <p>Rida ${seat.rowNumber}, koht ${seat.seatLetter}
            ${seat.seatWindow ? " (Akna all)" : ""}
            ${seat.extraLegroom ? " (Rohkem jalaruumi)" : ""}
            ${seat.nearExit ? " (Lähedal väljapääsule)" : ""}
            </p>
        `;
    });
}

function fetchSeatsForFlight(flightId) {
    fetch(`/api/seats/flight/${flightId}`)
        .then(response => response.json())
        .then(data => displaySeatLayout(data))
        .catch(error => console.error("Viga istekohtade laadimisel:", error));
}

function displaySeatLayout(seats) {
    const seatPlan = document.getElementById("seatPlan");
    seatPlan.innerHTML = "<h2>Istekohad lennul:</h2>";

    const seatGrid = document.createElement("div");
    seatGrid.className = "seat-grid";

    seats.forEach(seat => {
        const seatDiv = document.createElement("div");
        seatDiv.className = "seat";
        if (seat.occupied) {
            seatDiv.classList.add("occupied");
        } else {
            seatDiv.classList.add("available");
        }

        seatDiv.innerText = `${seat.rowNumber}${seat.seatLetter}`;
        seatGrid.appendChild(seatDiv);
    });

    seatPlan.appendChild(seatGrid);
}

