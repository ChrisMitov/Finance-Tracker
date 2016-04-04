<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<div id="wrapper">
	<nav class="navbar-default navbar-static-top" role="navigation">
		<div class="navbar-header">
			<h1>
				<a class="navbar-brand" href="index.html">Finance tracker</a>
			</h1>
			<div class="clearfix"></div>
		</div>
		<!-- Brand and toggle get grouped for better mobile display -->
		<!-- Collect the nav links, forms, and other content for toggling -->

		<div class="drop-men">
			<span class=" name-caret">Welcome, ${userName} !    </span>
		</div>
		<!-- /.navbar-collapse -->

		<div class="clearfix"></div>

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">

					<li><a href="index.html" class=" hvr-bounce-to-right"><i
							class="fa fa-calendar nav_icon "></i><span class="nav-label">Monthly
								overview</span> </a></li>

					<li><a href="#" class=" hvr-bounce-to-right"><i
							class="fa fa-minus-circle nav_icon"></i> <span class="nav-label">Expenses</span></a></li>
					<li><a href="inbox.html" class=" hvr-bounce-to-right"><i
							class="fa fa-plus-circle nav_icon"></i> <span class="nav-label">Incomes</span>
					</a></li>

					<li><a href="gallery.html" class=" hvr-bounce-to-right"><i
							class="fa fa-money nav_icon"></i> <span class="nav-label">Budget</span>
					</a></li>
					<li><a href="gallery.html" class=" hvr-bounce-to-right"><i
							class="fa fa-file-pdf-o nav_icon"></i> <span class="nav-label">Report</span>
					</a></li>
					<li><a href="./Profile.jsp" class=" hvr-bounce-to-right"><i
							class="fa fa-paint-brush nav_icon"></i> <span class="nav-label">Edit
								Profile</span></a></li>
					<li><a href="./LogOut.jsp" class=" hvr-bounce-to-right"><i
							class="fa fa-key nav_icon"></i> <span class="nav-label">Log
								out</span> </a></li>


				</ul>
			</div>
		</div>
	</nav>

