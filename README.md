# TP 9 - Proxy

### Proxy
- Proporciona un representante o sustituto de otro objeto para controlar el acceso a éste.

## Teléfonos de PersonaDAO
Hay clientes que no necesitan tener la colección de teléfonos, porque no invocan el método Persona#telefonos.
Se puede evitar realizar una consulta SQL de join utilizando el patrón proxy.