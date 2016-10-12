create table atenciones_detalles (id_atencion_detalle serial primary key, id_atencion integer not null, id_producto integer not null, cantidad integer not null default 0, id_usuario integer not null, created_at timestamp not null default now(), updated_at timestamp);
alter table atenciones_detalles add constraint atencion_detalle_fk foreign key (id_atencion) references atenciones(id_atencion) ;
alter table atenciones_detalles add constraint atencion_detalle_producto_fk foreign key (id_producto) references productos(id_producto) ;
alter table atenciones_detalles add constraint atencion_detalle_usuario_fk foreign key (id_usuario) references usuarios(id_usuario) ;
insert into atenciones_detalles(id_atencion, id_producto, cantidad, id_usuario, created_at)
(
select 3, id_producto,  (random() * 25 + 1)::integer, (random() * 1 + 1)::integer, now()  from productos  limit 50
);


-- select * from atenciones