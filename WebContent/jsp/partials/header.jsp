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
			<span class=" name-caret">Welcome, ${userName} ! </span>
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
							class="fa fa-usd nav_icon"></i> <span class="nav-label">Finance
								operations</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="./expenses" class=" hvr-bounce-to-right"><i
									class="fa fa-minus-circle nav_icon"></i>Expenses</a></li>
							<li><a href="signup.html" class=" hvr-bounce-to-right"><i
									class="fa fa-plus-circle nav_icon"></i>Incomes</a></li>
						</ul></li>
					<li><a href="./account" class=" hvr-bounce-to-right"><i
							class="fa fa-bank nav_icon "></i><span class="nav-label">Account</span>
					</a></li>
					<li><a href="./category" class=" hvr-bounce-to-right"><i
							class="fa fa-calendar-plus-o nav_icon"></i>Category</a></li>
					<li><a href="./budget" class=" hvr-bounce-to-right"><i
							class="fa fa-money nav_icon"></i> <span class="nav-label">Budget</span>
					</a></li>
					<li><a href="gallery.html" class=" hvr-bounce-to-right"><i
							class="fa fa-file-pdf-o nav_icon"></i> <span class="nav-label">Report</span>
					</a></li>
					<li><a href="./editprofile" class=" hvr-bounce-to-right"><i
							class="fa fa-paint-brush nav_icon"></i> <span class="nav-label">Edit
								Profile</span></a></li>
					<li><a href="./logout" class=" hvr-bounce-to-right"><i
							class="fa fa-key nav_icon"></i> <span class="nav-label">Log
								out</span> </a></li>


				</ul>
			</div>
		</div>
	</nav>