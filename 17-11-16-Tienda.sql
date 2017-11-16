drop database if exists ImpasseMision;
create database ImpasseMision;
use ImpasseMision;

create table empleado
(
	idEmpleado int,
	nombre varchar(45),
    primary key(cedula)
);


create table producto
(
	idProducto int,
	nombre varchar(45),
    precio int,
    primary key(idProducto)
);


create table detalle
(
	idDetalle int,
	nombreCliente varchar(45),
    cedula int,
    fecha varchar(45),
    primary key(idDetalle)
);


create table factura
(
	idFactura int,
	idDetalle int,
    idProducto int,
	cantidad int,
    total int,
    metodoPago varchar(45),
    primary key(idFactura),
    foreign key(idDetalle) references detalle(idDetalle),
    foreign key(idProducto) references producto(idProducto)
);