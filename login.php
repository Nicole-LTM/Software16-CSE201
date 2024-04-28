<?php
ob_start();
// Database connection settings
$host = "localhost";
$username = "root";
$password = ""; // Assuming empty password for local development
$database = "superstatssearcher";

// Create connection
$conn = new mysqli($host, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Handle form submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST["username"];
    $password = $_POST["password"];

    // Prepare SQL statement to prevent SQL injection
    $stmt = $conn->prepare("SELECT * FROM users WHERE username = ?");
    $stmt->bind_param("s", $username);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $user = $result->fetch_assoc();
        // Verify password using password_verify if using hashed passwords
        if (password_verify($password, $user["password"])) {
            header("Location: index.html");
            exit();
        } else {
            // Login failed
            echo "Invalid username or password.";
        }
    } else {
        // Login failed
        echo "Invalid username or password.";
    }
}

// Close statement and connection
$stmt->close();
$conn->close();
ob_end_flush();
?>