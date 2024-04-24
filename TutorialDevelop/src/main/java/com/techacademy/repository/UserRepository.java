package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.User;

// JpaRepositoryインターフェースから提供されるメソッドを使用可能
// - findAll, findById, save, deleteById
// - JpaRepositoryを継承しているので@Repositoryアノテーション省略可
public interface UserRepository extends JpaRepository<User, Integer> {

}
