package com.project.gymcenter.dao.form;

public class ReservationSearchForm {

    private String customerFilter;
    private String reservationSortBy;

    public ReservationSearchForm() {

        super();
    }

    public String getCustomerFilter() {
        return customerFilter;
    }

    public void setCustomerFilter(String customerFilter) {
        this.customerFilter = customerFilter;
    }

    public String getReservationSortBy() {
        return reservationSortBy;
    }

    public void setReservationSortBy(String reservationSortBy) {
        this.reservationSortBy = reservationSortBy;
    }
}
