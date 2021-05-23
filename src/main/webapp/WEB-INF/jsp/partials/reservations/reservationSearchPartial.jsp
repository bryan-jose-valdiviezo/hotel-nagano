<%-- 
    Document   : reservationSearchPartial
    Created on : May 22, 2021, 2:02:26 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container custom_container">
    <h2>Look for a reservation</h2>
    <form>
        <div class="row">
            <div class="form-group col-lg-6">
                <label for="email">Email</label>
                <input type="text" name="email" class="form-control">
            </div>

            <div class="form-group col-lg-6">
                <label for="reservation_number">Reservation Number</label>
                <input type="number" name="reservation_number" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <input type="submit" value="Search" class="submit-button">
        </div>
    </form>
</div>