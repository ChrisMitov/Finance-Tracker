<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="panel panel-danger">
	<form method="get" action="./login">
		<div class="panel-heading">
			<h4>Exchange rates</h4>
		</div>
		<table>
			<c:forEach var="entity" items="${rates}">
				<tr class="danger">
					<td class="danger">1 ${base}</td>
					<td class="danger">=</td>
					<td class="danger">${entity.value}${ entity.key}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</div>