consultas usada para montar os suspeitos

----todos caminhoa azul da ford ---------

select p.nome
from pessoa p join proprietario_pessoa_fisica f on p.id = f.pessoa_id 
join proprietario pr on pr.id = f.proprietario_id join proprietario_veiculo pv on pv.proprietario_id = pr.id
join veiculo v on v.id = pv.veiculo_id join modelo_veiculo m ON v.modelo_id = m.id 
WHERE v.cor = 'azul' AND LOWER(m.fabricante) = 'ford' AND LOWER(m.tipo) = 'caminhonete'

------ pessoas q tem características do suspeitos -----------------------

select p.nome from pessoa_caracteristica c join pessoa p on p.id = c.pessoa_id
where c.cor_cabelo = 'vermelho' and c.formato_cabelo = 'longo' and c.cor_pele = 'laranja' 
and c.altura = 'alto' and c.raca in ('terra-média','urban') and LOWER(c.barba) = 'sim'


------ pessoas q passaram no pedagio na hora do crime ---------

select p.nome from investigacao_pedagio i
join veiculo v on i.placa = v.placa
join proprietario_veiculo pv on pv.veiculo_id = v.id
join proprietario pr on pr.id = pv.proprietario_id
join proprietario_pessoa_fisica f on f.proprietario_id = pr.id
join pessoa p on p.id = f.pessoa_id
where i.data_hora BETWEEN '2024-11-18 09:30:00' AND '2024-11-18 12:00:00'

------ pessoas q fizeram ligacao suspeitas ---------


SELECT p.nome FROM investigacao_telefone i
JOIN proprietario_telefone pt ON pt.telefone_id = i.destino_telefone_id
JOIN proprietario pr ON pt.proprietario_id = pr.id
JOIN proprietario_pessoa_fisica f ON f.proprietario_id = pr.id
JOIN pessoa p ON p.id = f.pessoa_id
WHERE i.data_hora BETWEEN '2024-11-18 08:30:00' AND '2024-11-18 11:00:00'

UNION

SELECT p.nome FROM investigacao_telefone i
JOIN proprietario_telefone pt ON pt.telefone_id = i.origem_telefone_id
JOIN proprietario pr ON pt.proprietario_id = pr.id
JOIN proprietario_pessoa_fisica f ON f.proprietario_id = pr.id
JOIN pessoa p ON p.id = f.pessoa_id
WHERE i.data_hora BETWEEN '2024-11-18 08:30:00' AND '2024-11-18 11:00:00'