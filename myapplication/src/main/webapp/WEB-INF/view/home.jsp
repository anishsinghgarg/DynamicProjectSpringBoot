<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="UTF-8">
<head>
<title>Home</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body, h1 {
	font-family: "Raleway", sans-serif
}

body, html {
	height: 100%
}

.bgimg {
	min-height: 100%;
	background-position: center;
	background-size: cover;
}
</style>
<body>
	<div
		class="bgimg w3-display-container w3-animate-opacity w3-text-black">
		<div class="w3-display-middle">
			<h1 class="w3-jumbo w3-animate-top">Welcome : <font color="green">${model.userName} </font></h1>
			<hr class="w3-border-grey" style="margin: auto; width: auto;">
			<!-- <p class="w3-large w3-center">Anish Singh</p> -->
		</div>

	</div>

</body>
</html>