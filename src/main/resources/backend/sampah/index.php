<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, DELETE, PUT, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// Function to send JSON response
function sendJsonResponse($status, $message, $data = null) {
    $response = [
        "status" => $status,
        "message" => $message
    ];
    if ($data !== null) {
        $response["data"] = $data;
    }
    echo json_encode($response);
    exit;
}

// Database connection
$conn = pg_connect("host=localhost dbname=account user=vigiaa password=vigia");
if (!$conn) {
    sendJsonResponse("error", "Database connection failed");
}

// Handle OPTIONS request for CORS
if ($_SERVER["REQUEST_METHOD"] === "OPTIONS") {
    http_response_code(200);
    exit;
}

if ($_SERVER["REQUEST_METHOD"] === "GET") {
$result = pg_query($conn, "SELECT * FROM sampah");
        $data = pg_fetch_all($result);
        sendJsonResponse("success", "Tasks retrieved successfully", $data);
}
pg_close($conn);
?>
