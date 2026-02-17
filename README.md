# HomeProxy: Standalone Mobile Proxy for Android

HomeProxy is an Android application that enables you to create a mobile proxy farm using your Android phones without depending on any external provider.

## Features
- Create up to 15 mobile proxy endpoints on a single Android device.
- Uses your 4G/5G mobile data connection for high-quality proxies.
- Standalone: You host your own tunneling server (FRP).
- Rotate/Change IP manually or automatically.
- API for rotating the IP.

## How to Use
1. **Set up your own server**: Follow the [SERVER_SETUP.md](file:///Users/daniel/proxidize-android/SERVER_SETUP.md) guide.
2. **Install the App**: Build and install the APK on your Android 7+ device.
3. **Configure**: Enter your VPS details in the "Custom Server" section.
4. **Connect**: Hit "Connect" and start using your 15 proxy endpoints.

## Architecture
HomeProxy uses FRP (Fast Reverse Proxy) to tunnel connections from your phone to a public VPS. This allows you to access the mobile proxy from anywhere on the internet.

---
*Based on the Proxidize Android Legacy proof of concept.*
