<!DOCTYPE html>
<html>
<body>
<h2>Login</h2>
<form action="/home/login" method="POST">
<input type="text" name="emailId" placeholder="Email address" required autofocus/> <br> <br>
<input type="password" name="password" placeholder="Password" required autofocus/> <br> <br>
<button type="Submit" value="login"> Sign In </button> <br> <br>
${ERROR}
</form>
<input type="button" name="register" value="Sign Up" onclick="window.location.href='register.jsp'"/>
</body>
</html>