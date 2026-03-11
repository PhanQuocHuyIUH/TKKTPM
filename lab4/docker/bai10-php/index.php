<?php
?>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <title>Hello Docker PHP</title>
  <style>
    body { font-family: Arial, sans-serif; text-align: center; padding: 50px; background: #f4f4f4; }
    .card { background: white; padding: 30px; border-radius: 8px; display: inline-block; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    h1 { color: #4a4a8a; }
    table { margin: 20px auto; border-collapse: collapse; }
    td { padding: 6px 16px; text-align: left; border-bottom: 1px solid #eee; }
    td:first-child { font-weight: bold; color: #555; }
  </style>
</head>
<body>
  <div class="card">
    <h1>Hello, Docker PHP!</h1>
    <p>PHP application running inside Docker with Apache.</p>
    <table>
      <tr><td>PHP Version</td><td><?php echo PHP_VERSION; ?></td></tr>
      <tr><td>Server Software</td><td><?php echo $_SERVER['SERVER_SOFTWARE']; ?></td></tr>
      <tr><td>Document Root</td><td><?php echo $_SERVER['DOCUMENT_ROOT']; ?></td></tr>
      <tr><td>Hostname</td><td><?php echo gethostname(); ?></td></tr>
      <tr><td>Server Time</td><td><?php echo date('Y-m-d H:i:s'); ?></td></tr>
    </table>
  </div>
</body>
</html>
