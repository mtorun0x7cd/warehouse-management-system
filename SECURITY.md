# Security Policy

## Status

This repository is an archived academic project (TH Köln, Software-Praktikum,
Summer 2019), retained for reference and portfolio purposes. It is **not under
active development** and receives no functional or security updates.

## Not for production use

The application was written as a student exercise and predates any security
review. It is intended to run only in a controlled local environment and must
not be exposed to an untrusted network or operated against real data.

## Known Limitations

The development persistence unit in
`src/WAWIDBModel/src/META-INF/persistence.xml` carries default credentials
(`root` / `wawipassword`) for a disposable local MySQL instance used during
development and continuous integration; these are not secrets. EclipseLink is
configured with `drop-and-create-tables`, so the schema is dropped and
recreated on every run. Do not reuse these credentials, expose the application
to an untrusted network, or run it against a database whose contents must
survive.

## Reporting

To report a substantive issue worth recording, contact info@mtorun0x7cd.com.
Given the archived status of the project, a fix or response is not guaranteed.
