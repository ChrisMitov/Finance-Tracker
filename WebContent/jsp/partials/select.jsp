
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<span style="float: left;"> <label> Choose period: </label> <select
	class="option3" name="month">
		<c:forEach var="i" begin="1" end="12">
			<c:choose>
				<c:when test="${ sessionScope.month == i }">
					<option value="<c:out value = "${i}" />" selected><c:out
							value="${i}" /></option>
				</c:when>
				<c:otherwise>
					<option value="<c:out value = "${i}" />"><c:out
							value="${i}" /></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
</select> <select class="option3" name="year">
		<c:forEach var="i" begin="${sessionScope.year-5}"
			end="${sessionScope.year+5}">
			<c:choose>
				<c:when test="${ sessionScope.year == i }">
					<option value="<c:out value = "${i}" />" selected><c:out
							value="${i}" /></option>
				</c:when>
				<c:otherwise>
					<option value="<c:out value = "${i}" />"><c:out
							value="${i}" /></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
</select> <span style="float: right"><input type="submit" value="Change"
		class="btn btn-sm btn-danger warning_1"
		style="margin: 0 auto; margin-left: 10px; display: block;" /></span>
</span>