# HomeProxy Server Setup

To use HomeProxy, you need to set up an **FRP (Fast Reverse Proxy)** server on a Linux VPS. This server will receive the traffic from your Android phone and expose it as a proxy.

## 1. Get a VPS
You can use any cloud provider (DigitalOcean, AWS, Linode, Hetzner, etc.). A simple $5/month instance with Ubuntu 22.04 or similar is enough.

## 2. Install FRP Server (frps)
Connect to your VPS via SSH and run:

```bash
# Update and install dependencies
sudo apt update && sudo apt install -y curl

# Download FRP (Check for latest version at https://github.com/fatedier/frp/releases)
VER="0.54.0"
curl -L -o frp.tar.gz https://github.com/fatedier/frp/releases/download/v${VER}/frp_${VER}_linux_amd64.tar.gz
tar -xzf frp.tar.gz
cd frp_${VER}_linux_amd64
```

## 3. Configure frps.ini
Create a file named `frps.ini` (or `frps.toml` for newer versions, but this project uses `.ini` style tokens):

```ini
[common]
bind_port = 7000
token = YOUR_SECRET_TOKEN
```
> [!IMPORTANT]
> Change `YOUR_SECRET_TOKEN` to something strong and matching what you enter in the Android app.

## 4. Open Firewall Ports
You need to open the communication port (7000) and the proxy ports (e.g., 10000 to 10015).

```bash
sudo ufw allow 7000/tcp
sudo ufw allow 10000:10015/tcp
sudo ufw enable
```

## 5. Run the Server
```bash
./frps -c ./frps.ini
```
To keep it running in the background, use `screen`, `nohup`, or create a systemd service.

## 6. Configure the Android App
1. Open **HomeProxy** on your phone.
2. Click on **Custom Server**.
3. Enter your **VPS IP**, Port **7000**, and your **Secret Token**.
4. Save and click **Connect**.
5. Your 15 proxy endpoints will be available at `YOUR_VPS_IP:10000` through `YOUR_VPS_IP:10014`.
