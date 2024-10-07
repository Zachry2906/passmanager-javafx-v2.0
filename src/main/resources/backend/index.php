<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, DELETE, PUT, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

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

// Handle OPTIONS request
if ($_SERVER["REQUEST_METHOD"] === "OPTIONS") {
    http_response_code(200);
    exit;
}

if ($_SERVER["REQUEST_METHOD"] === "GET") {

    // Handle DELETE request via GET method
    if (isset($_GET["method"]) && $_GET["method"] === "DELETE") {
        if (isset($_GET["id"])) {
            $id = $_GET["id"];
            if($_GET["table"] == "data"){
            $query = "DELETE FROM people WHERE id = $1";
            } else {
            $query = "DELETE FROM sampah WHERE id = $1";
            }
            $result = pg_query_params($conn, $query, array($id));
            if ($result) {
                sendJsonResponse("success", "Record deleted successfully");
            } else {
                sendJsonResponse("error", "Failed to delete record");
            }
        } else {
            sendJsonResponse("error", "ID is required for deletion");
        }
    }

    // Handle PUT request via GET method
    elseif (isset($_GET["method"]) && $_GET["method"] === "PUT") {
        if (isset($_GET["id"], $_GET["web"], $_GET["note"], $_GET["pass"])) {
            $id = $_GET["id"];
            $web = $_GET["web"];
            $note = $_GET["note"];
            $pass = $_GET["pass"];

            $query = "UPDATE people SET web = $1, note = $2, password = $3 WHERE id = $4";
            $result = pg_query_params($conn, $query, array($web, $note, $pass, $id));

            if ($result) {
                sendJsonResponse("success", "Record updated successfully");
            } else {
                sendJsonResponse("error", "Failed to update record");
            }
        } else {
            sendJsonResponse("error", "Missing required fields for update");
        }
    }

    // Handle UPDATE favorite via GET method
    elseif (isset($_GET["favorite"], $_GET["id"])) {
        $id = $_GET["id"];
        $favorite = $_GET["favorite"];
        $query = "UPDATE people SET favorite = $1 WHERE id = $2";
        $result = pg_query_params($conn, $query, array($favorite, $id));

        if ($result) {
            sendJsonResponse("success", "Favorite status updated successfully");
        } else {
            sendJsonResponse("error", "Failed to update favorite status");
        }
    }

    // Handle SELECT only favorites via GET method
    elseif (isset($_GET["fav"])) {
        $result = pg_query($conn, "SELECT * FROM people WHERE favorite = true");
        if ($result) {
            $data = pg_fetch_all($result);

            sendJsonResponse("success", "Records retrieved successfully", $data);
        } else {
            sendJsonResponse("error", "Failed to retrieve records");
        }
    }

    // Handle general SELECT request
    else {
        if (isset($_GET["isi"]) && $_GET["isi"] === "sampah") {
            $result = pg_query($conn, "SELECT * FROM sampah");
        } else {
            $result = pg_query($conn, "SELECT * FROM people");
        }

        if ($result) {
            $data = pg_fetch_all($result);

            sendJsonResponse("success", "Records retrieved successfully", $data);
        } else {
            sendJsonResponse("error", "Failed to retrieve records");
        }
    }
}

// Handle POST requests
elseif ($_SERVER["REQUEST_METHOD"] === "POST") {
    if (isset($_POST["web"], $_POST["note"], $_POST["password"], $_POST["email"], $_POST["name"], $_POST["waktu"], $_POST["img"], $_POST["type"])) {
        $web = $_POST["web"];
        $note = $_POST["note"];
        $password = $_POST["password"];
        $email = $_POST["email"];
        $name = $_POST["name"];
        $waktu = $_POST["waktu"];
        $img = $_POST["img"];
        $type = $_POST["type"];
$fav = $_POST["favorite"];

if ($type === "add") {
    $query = "INSERT INTO people (web, note, password, email, name, waktu, img, favorite) VALUES ($1, $2, $3, $4, $5, $6, $7, $8)";
} else {
    $query = "INSERT INTO sampah (web, note, password, email, name, waktu, img, favorite) VALUES ($1, $2, $3, $4, $5, $6, $7, $8)";
}

$result = pg_query_params($conn, $query, array($web, $note, $password, $email, $name, $waktu, $img, $fav));

        if ($result) {
            sendJsonResponse("success", "Record added successfully");
        } else {
            sendJsonResponse("error", "Failed to add record");
        }
    } else {
        sendJsonResponse("error", "Missing required fields for POST request");
    }
} else {
    sendJsonResponse("error", "Invalid request method");
}

pg_close($conn);
?>
