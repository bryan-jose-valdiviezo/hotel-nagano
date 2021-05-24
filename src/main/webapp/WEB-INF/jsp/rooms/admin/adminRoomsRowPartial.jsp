<%-- 
    Document   : adminRoomsRowPartial
    Created on : May 23, 2021, 8:29:03 AM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="row">
    <div class="col-sm-3">
        ${room.id}
    </div>

    <div class="col-sm-3">
        ${room.suite.name}
    </div>

    <div class="col-sm-3">
        Floor: ${room.floor}
    </div>

    <div class="col-sm-3">
        ${room.view.name}
    </div>
</div>