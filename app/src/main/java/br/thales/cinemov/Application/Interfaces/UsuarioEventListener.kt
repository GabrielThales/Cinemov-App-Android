package br.thales.cinemov.Application.Interfaces

import br.thales.cinemov.Domain.Entities.Usuario

interface UsuarioEventListener : FirebaseEventListener<Usuario> {
}