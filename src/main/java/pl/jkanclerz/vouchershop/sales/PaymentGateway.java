package pl.jkanclerz.vouchershop.sales;

interface PaymentGateway {
    PaymentDetails registerFor(Reservation reservation, ClientData clientData);
}
