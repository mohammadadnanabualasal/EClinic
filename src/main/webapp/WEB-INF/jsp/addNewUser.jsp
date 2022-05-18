<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form role="form" class="login-form" action="/addNewUser" method="post">
            <div class="form-group">
                <label for="name">
                    Name
                </label>
                <input type="text" class="form-control" id="name"  name="name"/>
            </div>
            <div class="form-group">
                <label for="email">
                    Email address
                </label>
                <input type="email" class="form-control" id="email"  name="email"/>
            </div>
            <div class="form-group">
                <label for="permission">User Role</label>
                <select name="permission" id="permission" class="form-select form-select-lg mb-3"
                        aria-label="Role">
                        <option value="admin">Admin</option>
                        <option value="doctor">Doctor</option>
                        <option value="secretary" selected>Secretary</option>
                </select>
            </div>
            <div class="form-group">
                <label for="phone">
                    phone
                </label>
                <input type="text" class="form-control" id="phone" name="phone"/>
            </div>
            <div class="form-group">
                <label for="password">
                    Password
                </label>
                <input type="password" class="form-control" id="password" name="password"/>
            </div>
            <div class="form-group">
                <label for="confirmPassword">
                    Confirm your Password
                </label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"/>
            </div>
            <button type="submit" class="btn btn-primary">
                Create New User
            </button>
        </form>

    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>
