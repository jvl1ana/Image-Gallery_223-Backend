--USERS
insert into users (id, email, first_name, last_name, password)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'admin@example.com', 'James', 'Bond',
        '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6'), -- Password: 1234
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'user@example.com', 'Tyler', 'Durden',
        '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6'),  -- Password: 1234
       ('3a97edf9-b481-4f33-9b4f-ef4104b15df1', 'max@example.com', 'Max', 'Max',
        '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6')  -- Password: 1234
    ON CONFLICT DO NOTHING;


--ROLES
INSERT INTO role(id, name)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'DEFAULT'),
       ('ab505c92-7280-49fd-a7de-258e618df074', 'ADMIN'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'USER') ON CONFLICT DO NOTHING;

--AUTHORITIES
INSERT INTO authority(id, name)
VALUES ('2ebf301e-6c61-4076-98e3-2a38b31daf86', 'DEFAULT'),
       ('76d2cbf6-5845-470e-ad5f-2edb9e09a868', 'USER_MODIFY'),
       ('21c942db-a275-43f8-bdd6-d048c21bf5ab', 'USER_DELETE'),
       ('9b0118de-3ceb-4bcd-8058-d4bd77636ae2', 'IMAGE_CREATE'),
       ('16d12f02-d4a5-43ce-9f5e-e778f6816c3e', 'IMAGE_DELETE'),
       ('96f38485-f629-42c8-8207-5f4ea593e09f', 'IMAGE_READ'),
       ('fcf14907-471d-4ede-ad96-9fe0f6d6dba0', 'IMAGE_UPDATE') ON CONFLICT DO NOTHING;

--assign roles to users
insert into users_role (users_id, role_id)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'ab505c92-7280-49fd-a7de-258e618df074'),
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'c6aee32d-8c35-4481-8b3e-a876a39b0c02'),
       ('3a97edf9-b481-4f33-9b4f-ef4104b15df1', 'c6aee32d-8c35-4481-8b3e-a876a39b0c02')
    ON CONFLICT DO NOTHING;

--assign authorities to roles
INSERT INTO role_authority(role_id, authority_id)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '2ebf301e-6c61-4076-98e3-2a38b31daf86'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '76d2cbf6-5845-470e-ad5f-2edb9e09a868'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '21c942db-a275-43f8-bdd6-d048c21bf5ab') ON CONFLICT DO NOTHING;

-- USER role has CRUD authorities
INSERT INTO role_authority(role_id, authority_id)
VALUES
    -- IMAGE_CREATE
    ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '9b0118de-3ceb-4bcd-8058-d4bd77636ae2'),
    -- IMAGE_DELETE
    ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '16d12f02-d4a5-43ce-9f5e-e778f6816c3e'),
    -- IMAGE_READ
    ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '96f38485-f629-42c8-8207-5f4ea593e09f'),
    -- IMAGE_UPDATE
    ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'fcf14907-471d-4ede-ad96-9fe0f6d6dba0') ON CONFLICT DO NOTHING;


-- ADMIN role has CRUD authorities
INSERT INTO role_authority(role_id, authority_id)
VALUES
    -- IMAGE_CREATE
    ('ab505c92-7280-49fd-a7de-258e618df074', '9b0118de-3ceb-4bcd-8058-d4bd77636ae2'),
    -- IMAGE_DELETE
    ('ab505c92-7280-49fd-a7de-258e618df074', '16d12f02-d4a5-43ce-9f5e-e778f6816c3e'),
    -- IMAGE_READ
    ('ab505c92-7280-49fd-a7de-258e618df074', '96f38485-f629-42c8-8207-5f4ea593e09f'),
    -- IMAGE_UPDATE
    ('ab505c92-7280-49fd-a7de-258e618df074', 'fcf14907-471d-4ede-ad96-9fe0f6d6dba0') ON CONFLICT DO NOTHING;


-- Testdata for ImagePost
INSERT INTO image_post (id, url, description, author_id)
VALUES
-- ImagePosts from James Bond (Admin)
('74d46555-9466-4c17-88b4-fbb90807395d', 'https://example.com/image1.jpg', 'A beautiful sunset over the mountains.',
 'ba804cb9-fa14-42a5-afaf-be488742fc54'),
('c57e1b69-6fa4-42ae-87ff-df02d0a4b8c4', 'https://example.com/image2.jpg',
 'An exciting scene from a recent hiking trip.', 'ba804cb9-fa14-42a5-afaf-be488742fc54'),

-- ImagePosts from Tyler Durden (User)
('d9e3f2b4-e518-4a37-8a9e-4b3c0a64805e', 'https://example.com/image3.jpg',
 'A shot of the bustling city streets at night.', '0d8fa44c-54fd-4cd0-ace9-2a7da57992de'),
('afb67c9b-cae5-4709-9f8d-e84236cb1e92', 'https://example.com/image4.jpg',
 'A serene beach with clear waters and white sand.', '0d8fa44c-54fd-4cd0-ace9-2a7da57992de') ON CONFLICT DO NOTHING;


-- Likes between Users and ImagePosts
INSERT INTO users_like (post_id, user_id)
VALUES
    -- James Bond (admin) liked some posts
    ('d9e3f2b4-e518-4a37-8a9e-4b3c0a64805e', 'ba804cb9-fa14-42a5-afaf-be488742fc54'),
    ('afb67c9b-cae5-4709-9f8d-e84236cb1e92', 'ba804cb9-fa14-42a5-afaf-be488742fc54'),
    ('afb67c9b-cae5-4709-9f8d-e84236cb1e92', 'ba804cb9-fa14-42a5-afaf-be488742fc54'),

    -- Tyler Durden liked some posts
    ('74d46555-9466-4c17-88b4-fbb90807395d', '0d8fa44c-54fd-4cd0-ace9-2a7da57992de'),
    ('c57e1b69-6fa4-42ae-87ff-df02d0a4b8c4', '0d8fa44c-54fd-4cd0-ace9-2a7da57992de'),

    -- Max liked some posts
    ('74d46555-9466-4c17-88b4-fbb90807395d', '3a97edf9-b481-4f33-9b4f-ef4104b15df1'),
    ('d9e3f2b4-e518-4a37-8a9e-4b3c0a64805e', '3a97edf9-b481-4f33-9b4f-ef4104b15df1')
    ON CONFLICT DO NOTHING;

