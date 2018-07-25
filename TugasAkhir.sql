PGDMP         (                v         
   TugasAkhir    9.4.15    9.4.15 W    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    34194 
   TugasAkhir    DATABASE     �   CREATE DATABASE "TugasAkhir" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE "TugasAkhir";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    16            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    16                        2615    35763    tiger    SCHEMA        CREATE SCHEMA tiger;
    DROP SCHEMA tiger;
             postgres    false                        2615    36033 
   tiger_data    SCHEMA        CREATE SCHEMA tiger_data;
    DROP SCHEMA tiger_data;
             postgres    false                        2615    35585    topology    SCHEMA        CREATE SCHEMA topology;
    DROP SCHEMA topology;
             postgres    false                        3079    11855    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1                        3079    35756    address_standardizer 	   EXTENSION     H   CREATE EXTENSION IF NOT EXISTS address_standardizer WITH SCHEMA public;
 %   DROP EXTENSION address_standardizer;
                  false    16            �           0    0    EXTENSION address_standardizer    COMMENT     �   COMMENT ON EXTENSION address_standardizer IS 'Used to parse an address into constituent elements. Generally used to support geocoding address normalization step.';
                       false    6                        3079    35745    fuzzystrmatch 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS fuzzystrmatch WITH SCHEMA public;
    DROP EXTENSION fuzzystrmatch;
                  false    16            �           0    0    EXTENSION fuzzystrmatch    COMMENT     ]   COMMENT ON EXTENSION fuzzystrmatch IS 'determine similarities and distance between strings';
                       false    7                        3079    36347    ogr_fdw 	   EXTENSION     ;   CREATE EXTENSION IF NOT EXISTS ogr_fdw WITH SCHEMA public;
    DROP EXTENSION ogr_fdw;
                  false    16            �           0    0    EXTENSION ogr_fdw    COMMENT     L   COMMENT ON EXTENSION ogr_fdw IS 'foreign-data wrapper for GIS data access';
                       false    4            	            3079    34195    postgis 	   EXTENSION     ;   CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;
    DROP EXTENSION postgis;
                  false    16            �           0    0    EXTENSION postgis    COMMENT     g   COMMENT ON EXTENSION postgis IS 'PostGIS geometry, geography, and raster spatial types and functions';
                       false    9                        3079    36192 	   pgrouting 	   EXTENSION     =   CREATE EXTENSION IF NOT EXISTS pgrouting WITH SCHEMA public;
    DROP EXTENSION pgrouting;
                  false    16    9            �           0    0    EXTENSION pgrouting    COMMENT     9   COMMENT ON EXTENSION pgrouting IS 'pgRouting Extension';
                       false    5                        3079    36351 
   pointcloud 	   EXTENSION     >   CREATE EXTENSION IF NOT EXISTS pointcloud WITH SCHEMA public;
    DROP EXTENSION pointcloud;
                  false    16            �           0    0    EXTENSION pointcloud    COMMENT     G   COMMENT ON EXTENSION pointcloud IS 'data type for lidar point clouds';
                       false    3                        3079    36434    pointcloud_postgis 	   EXTENSION     F   CREATE EXTENSION IF NOT EXISTS pointcloud_postgis WITH SCHEMA public;
 #   DROP EXTENSION pointcloud_postgis;
                  false    3    9    16            �           0    0    EXTENSION pointcloud_postgis    COMMENT     n   COMMENT ON EXTENSION pointcloud_postgis IS 'integration for pointcloud LIDAR data and PostGIS geometry data';
                       false    2                        3079    35727    postgis_sfcgal 	   EXTENSION     B   CREATE EXTENSION IF NOT EXISTS postgis_sfcgal WITH SCHEMA public;
    DROP EXTENSION postgis_sfcgal;
                  false    9    16            �           0    0    EXTENSION postgis_sfcgal    COMMENT     C   COMMENT ON EXTENSION postgis_sfcgal IS 'PostGIS SFCGAL functions';
                       false    8                        3079    35764    postgis_tiger_geocoder 	   EXTENSION     I   CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder WITH SCHEMA tiger;
 '   DROP EXTENSION postgis_tiger_geocoder;
                  false    9    19    7            �           0    0     EXTENSION postgis_tiger_geocoder    COMMENT     ^   COMMENT ON EXTENSION postgis_tiger_geocoder IS 'PostGIS tiger geocoder and reverse geocoder';
                       false    11            
            3079    35586    postgis_topology 	   EXTENSION     F   CREATE EXTENSION IF NOT EXISTS postgis_topology WITH SCHEMA topology;
 !   DROP EXTENSION postgis_topology;
                  false    9    18            �           0    0    EXTENSION postgis_topology    COMMENT     Y   COMMENT ON EXTENSION postgis_topology IS 'PostGIS topology spatial types and functions';
                       false    10                       1259    90191    admin    TABLE     �   CREATE TABLE admin (
    id integer NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(64) NOT NULL
);
    DROP TABLE public.admin;
       public         postgres    false    16                       1259    90189    admin_id_seq    SEQUENCE     n   CREATE SEQUENCE admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.admin_id_seq;
       public       postgres    false    272    16            �           0    0    admin_id_seq    SEQUENCE OWNED BY     /   ALTER SEQUENCE admin_id_seq OWNED BY admin.id;
            public       postgres    false    271                       1259    90255    gridsby    TABLE     �   CREATE TABLE gridsby (
    id integer NOT NULL,
    geom geometry(MultiPolygon,4326),
    "left" numeric,
    bottom numeric,
    "right" numeric,
    top numeric
);
    DROP TABLE public.gridsby;
       public         postgres    false    16    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16                       1259    90253    gridsby_id_seq    SEQUENCE     p   CREATE SEQUENCE gridsby_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.gridsby_id_seq;
       public       postgres    false    16    274            �           0    0    gridsby_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE gridsby_id_seq OWNED BY gridsby.id;
            public       postgres    false    273            	           1259    36456    history    TABLE     y  CREATE TABLE history (
    gid integer NOT NULL,
    latitude numeric(12,8),
    longitude numeric(12,8),
    geom geometry,
    updatedat date,
    CONSTRAINT enforce_dims_geom CHECK ((st_ndims(geom) = 2)),
    CONSTRAINT enforce_geotype_geom CHECK (((geometrytype(geom) = 'POINT'::text) OR (geom IS NULL))),
    CONSTRAINT enforce_srid_geom CHECK ((st_srid(geom) = 4326))
);
    DROP TABLE public.history;
       public         postgres    false    9    16    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16    9    16    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16    9    16    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16    16                       1259    36454    history_gid_seq    SEQUENCE     q   CREATE SEQUENCE history_gid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.history_gid_seq;
       public       postgres    false    265    16            �           0    0    history_gid_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE history_gid_seq OWNED BY history.gid;
            public       postgres    false    264                       1259    90183    user_account    TABLE     �   CREATE TABLE user_account (
    id integer NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(64) NOT NULL,
    domisili character varying(100)
);
     DROP TABLE public.user_account;
       public         postgres    false    16                       1259    90181    user_account_id_seq    SEQUENCE     u   CREATE SEQUENCE user_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.user_account_id_seq;
       public       postgres    false    270    16            �           0    0    user_account_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE user_account_id_seq OWNED BY user_account.id;
            public       postgres    false    269                       1259    90172    user_mov    TABLE     �   CREATE TABLE user_mov (
    id integer NOT NULL,
    user_id integer,
    location geometry(Point,4326),
    updatedat timestamp without time zone
);
    DROP TABLE public.user_mov;
       public         postgres    false    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16    16                       1259    90170    user_mov_id_seq    SEQUENCE     q   CREATE SEQUENCE user_mov_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.user_mov_id_seq;
       public       postgres    false    268    16            �           0    0    user_mov_id_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE user_mov_id_seq OWNED BY user_mov.id;
            public       postgres    false    267                       1259    90283    user_position    TABLE     b   CREATE TABLE user_position (
    id integer NOT NULL,
    id_user integer,
    id_grid integer
);
 !   DROP TABLE public.user_position;
       public         postgres    false    16                       1259    90281    user_position_id_seq    SEQUENCE     v   CREATE SEQUENCE user_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.user_position_id_seq;
       public       postgres    false    16    276            �           0    0    user_position_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE user_position_id_seq OWNED BY user_position.id;
            public       postgres    false    275            
           1259    40963    user_seq    SEQUENCE     j   CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.user_seq;
       public       postgres    false    16            ?           2604    90194    id    DEFAULT     V   ALTER TABLE ONLY admin ALTER COLUMN id SET DEFAULT nextval('admin_id_seq'::regclass);
 7   ALTER TABLE public.admin ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    272    271    272            @           2604    90258    id    DEFAULT     Z   ALTER TABLE ONLY gridsby ALTER COLUMN id SET DEFAULT nextval('gridsby_id_seq'::regclass);
 9   ALTER TABLE public.gridsby ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    273    274    274            9           2604    36459    gid    DEFAULT     \   ALTER TABLE ONLY history ALTER COLUMN gid SET DEFAULT nextval('history_gid_seq'::regclass);
 :   ALTER TABLE public.history ALTER COLUMN gid DROP DEFAULT;
       public       postgres    false    264    265    265            >           2604    90186    id    DEFAULT     d   ALTER TABLE ONLY user_account ALTER COLUMN id SET DEFAULT nextval('user_account_id_seq'::regclass);
 >   ALTER TABLE public.user_account ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    269    270    270            =           2604    90175    id    DEFAULT     \   ALTER TABLE ONLY user_mov ALTER COLUMN id SET DEFAULT nextval('user_mov_id_seq'::regclass);
 :   ALTER TABLE public.user_mov ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    268    267    268            A           2604    90286    id    DEFAULT     f   ALTER TABLE ONLY user_position ALTER COLUMN id SET DEFAULT nextval('user_position_id_seq'::regclass);
 ?   ALTER TABLE public.user_position ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    275    276    276            �          0    90191    admin 
   TABLE DATA                     public       postgres    false    272   �R       �           0    0    admin_id_seq    SEQUENCE SET     3   SELECT pg_catalog.setval('admin_id_seq', 1, true);
            public       postgres    false    271            �          0    90255    gridsby 
   TABLE DATA                     public       postgres    false    274   AS       �           0    0    gridsby_id_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('gridsby_id_seq', 459, true);
            public       postgres    false    273            �          0    36456    history 
   TABLE DATA                     public       postgres    false    265   �w       �           0    0    history_gid_seq    SEQUENCE SET     6   SELECT pg_catalog.setval('history_gid_seq', 9, true);
            public       postgres    false    264            1          0    36353    pointcloud_formats 
   TABLE DATA                     public       postgres    false    262   y       2          0    34491    spatial_ref_sys 
   TABLE DATA                     public       postgres    false    187   (y       �          0    90183    user_account 
   TABLE DATA                     public       postgres    false    270   By       �           0    0    user_account_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('user_account_id_seq', 15, true);
            public       postgres    false    269            �          0    90172    user_mov 
   TABLE DATA                     public       postgres    false    268   "}       �           0    0    user_mov_id_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('user_mov_id_seq', 53, true);
            public       postgres    false    267            �          0    90283    user_position 
   TABLE DATA                     public       postgres    false    276   q~       �           0    0    user_position_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('user_position_id_seq', 4, true);
            public       postgres    false    275            �           0    0    user_seq    SEQUENCE SET     0   SELECT pg_catalog.setval('user_seq', 1, false);
            public       postgres    false    266            5          0    35770    geocode_settings 
   TABLE DATA                     tiger       postgres    false    209   �~       6          0    36125    pagc_gaz 
   TABLE DATA                     tiger       postgres    false    253          7          0    36137    pagc_lex 
   TABLE DATA                     tiger       postgres    false    255          8          0    36149 
   pagc_rules 
   TABLE DATA                     tiger       postgres    false    257   5       3          0    35589    topology 
   TABLE DATA                     topology       postgres    false    202   O       4          0    35602    layer 
   TABLE DATA                     topology       postgres    false    203   i       J           2606    90196 
   admin_pkey 
   CONSTRAINT     G   ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.admin DROP CONSTRAINT admin_pkey;
       public         postgres    false    272    272            L           2606    90260    gridsby_pkey 
   CONSTRAINT     K   ALTER TABLE ONLY gridsby
    ADD CONSTRAINT gridsby_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.gridsby DROP CONSTRAINT gridsby_pkey;
       public         postgres    false    274    274            D           2606    36467    history_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY history
    ADD CONSTRAINT history_pkey PRIMARY KEY (gid);
 >   ALTER TABLE ONLY public.history DROP CONSTRAINT history_pkey;
       public         postgres    false    265    265            H           2606    90188    user_account_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.user_account DROP CONSTRAINT user_account_pkey;
       public         postgres    false    270    270            F           2606    90180    user_mov_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY user_mov
    ADD CONSTRAINT user_mov_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.user_mov DROP CONSTRAINT user_mov_pkey;
       public         postgres    false    268    268            O           2606    90288    user_position_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY user_position
    ADD CONSTRAINT user_position_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.user_position DROP CONSTRAINT user_position_pkey;
       public         postgres    false    276    276            B           1259    36468    history_geom_gist    INDEX     =   CREATE INDEX history_geom_gist ON history USING gist (geom);
 %   DROP INDEX public.history_geom_gist;
       public         postgres    false    9    9    9    16    9    16    16    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16    9    16    16    265            M           1259    90264    sidx_gridsby_geom    INDEX     =   CREATE INDEX sidx_gridsby_geom ON gridsby USING gist (geom);
 %   DROP INDEX public.sidx_gridsby_geom;
       public         postgres    false    9    9    9    16    9    16    16    9    9    16    9    16    9    16    9    16    9    16    9    16    16    9    16    9    16    16    274            P           2606    90294    user_position_id_grid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY user_position
    ADD CONSTRAINT user_position_id_grid_fkey FOREIGN KEY (id_grid) REFERENCES gridsby(id) ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.user_position DROP CONSTRAINT user_position_id_grid_fkey;
       public       postgres    false    274    3916    276            Q           2606    90289    user_position_id_user_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY user_position
    ADD CONSTRAINT user_position_id_user_fkey FOREIGN KEY (id_user) REFERENCES user_account(id) ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.user_position DROP CONSTRAINT user_position_id_user_fkey;
       public       postgres    false    276    3912    270            �   �   x���v
Q���WHL���S��L�Q(-N-�K�M�Q(H,..�/J�Ts�	uV�0�QP+U2T�UT�*���M������C�̋K�+�Ms�\J�
+]�͊*=S�
K���5���� ;Y'b      �      x���݊�yV����Z��#2"񨺺D��uPFtN�{����X�?��k������S����_��������������������?��?������7������ӟ�ǿ�����O��������߿�Ϳ��������������?���ç?}ڞ����ٟ�>oO���_����I��嗿����._n?������tݎ��>�t�|\����,���������������9��>m�����>���/��_��������_��׍z{�z�/?}���|���긹{>�w�zg��Q�ǟ~��?޶����/��zn9�R�h�;[���s~޾n������y}T����9G�C���z�R=���_���t�������s�z���Z��K���/��x�<��������&�Z�x��9�_~��o0=������#�}����V������_�D��gӣ8n�Ϲ��_�ޭU�T�?�2�|���?��ss�|�Q�&���z�S+~|Ο~������Gy��=�s�J��Z�֮���}�ss�|̑Ov���kl�e�?>�o��e���qs�|ΑO����ll�m�?>�_����ҷ?��qs�|ΑO����Ҳ�����|��:�u{�����1G>}m��[k�����۟L?]�qs�|ΑO����ol���?>�_~���y�����9G>Y8z_�����~�� ȷ򰞛��c�z���ƱMWG��޾+��#s��w��Y�t+�6�9R!��1���B��.���ڲOw����pK>7�Z󝚯�ڲOw�Ԉ�p���Qk�C��~s�ӝ#EbB�y�Ԛo�|�ז}�s�JL�[�y�Ԛo�|�zӕ#UbZ�y��ZO���t�n)��X��S��z�P���t�n)��|nB��i���ӕ#�bZ�y*՚��jT��v+�w����<�jͧ;G�J�����Y�{>�Ow�����I1-�y�<�jͧ;G�J���a%��s�P�5^\j;���9�����S��|�s���h�N��q���Tk>�9z_���a!��s������Q�1�9e:�{[�?6w��J7�ҽl��t�H���>n�R5�]�_��e�u�VL�[��yh��wj�ޛ�t�H���>n�b5�گ��c�w�ZL�c7O��v�W{w9��G�������\�~����.)����S�F>���s�x�^L�c7O�Z��^&�s�y�bL�[��y(��w�~53=��GJ�D9�q����jhz��k�w����<4k������gk����؎}��=�s����Ǧgk�pjL�c7O��t�����>,+�~n�5�����}���ù1e�}�<uk����'�������6�>n��5�����7*[ۇ�e����C�F>�׳��t�p����Ǘ�R�-����g����Q���t�H�6�>n��5�]�_���2�>R;��-��<�k�;�_���2�>R;�ͱ���v�~������n������S�F�]���^��Gj�Ĺ����v�~����.)���S�F>�׳��t�H�6�<n�ҵ�{�ӣl����#�c�����C�F�������t�H�6�>n��5�]�_�N�[ۇ����~n�5���ѳ�����������{>���G�N�[ۇ�c���yj���G�N�[ۇ�e�����C�F?��Զ������6�>n��5���ѳ������8�>n��5��������K˰�%���t�|R�g�����٦���m�#s�����P;=�vz�n������S�F������\��GjǴ�����v�~����D�n������S�F�C��~}\��GjǴ9�q�Ԯ�o�~����t�H�8��s�Ю�o�~�|��#�c��yJ��'�zvz��)����S��|�vz���:�=R;��-��<�k��i���^��GjǴ9�q�Ԯ���jvzmm֖�>��yh���G�No��?��;�qs�|��O�����Zۇ�c���yj���G�No����2ln���]��_j�ǭ�}8;�ͱ���v�~�}�����>�����S�F?�>z_���ai6��s󐮑O����6�>�m:�{�����o�I?w��NO��K�No��#�c���yj��w�~�������1mn���]�ߩ�zo/��#�c���yj��wh�گ������1m�}�<�k�۵_���e�}�vL�[��yh��i�Z����1q�y�<�k�z=;}��)����S��|�vz)���t�H�6��s�Ю���jv�2]>R;�ͱ���v�~W�W�ӗ��am��󰟛�v�~�}�����}��wv�>n��9���ѳӗ����6�>n��5���ѳӗ��am6��s�Ю��/��<O����1q���<�k���xꩵ8=&�1���z��������2xt���^���+^�z�n �7�߽m��������Ӌ���{G���$d�y
�xр�ÖO�$d
���C�F�S�N[>M����It��)a#���C�[Hj�4:r�԰p׀ŗ���"2�n	��!b#�{��kH��d:�q���O��u_6O�L�>n�*��{e��׏z�[H��t�%������7Xs�m|�<�dB�y
�xՀ5H����SI��<��d#��!=I���SJ&�1����s�����(u�8O-�V�@n�Z6�ҳ�m|�<�dZ���C�F@�������T�iu��f#��!EL�:O5�X�@n�j6�R������ҥ�����~�����k�;��]��_��������g��kS���SM��1�������{�_<O5�V�t�P��Ԁ������T�iu��f#�{�C�g�SM��1��������Č/���L�[�y���i�^���b2��}�<�l��|EL�>O1�V�>n�b��{��kS���SM��-�<�l�i����L�c 7O5������&�t�P�P��"��o���L�c 7w��u)b��
z�ɴ:r�T�P��"���L�[�y���ט�2>��j2����<�l�=����k�&���S�F@�C�_��b�X��s���O�1u|�a����?��?2w�S����6��ϡ��L�c 7O5/��3�CRM��-�<�l<5`�%f|=�dZ�y��xh����I�T�iu��f#�{/1��&떀nj6n��o����L�c7O1�$_S�G�SL�ձ������^1�����U�T�iuK@75o�����&����S�F���a��.z������<�l�=������&������9G@�C��:���j2����<�l�=������&�ꖀnj6�5��������L�c 7O5u)b��<z���:r�T�P������Xl�.��<�l��|EL�Gw��,�mkؿ|d����S_ژ:���j2����<�l�h��K��@z�ɴ�%�������,��L��T�iu��f#���C�{H�ɴ:r�T�p׀ŗ���j2�n	��f#�k��W�SL&ֱ�����~�����+�)&�����S��~��������T�iuK@75o����;�&����S�F���a��Pz������<�l�=����K�&������9G@�C��:>��j2����<�l�=����[�&�ꖀnj6�5��������L�c 7O5u)b��^z���:r�T�P���p|/=�db����C�F?�W���t�����~ޏ�����������?���b2��}�<�l��H��+��^zjɔ�������~�����不�L�c7O)��W��1�ARI�ӱ�����~��뽿�/���L�[��y��I�^���22��y�<el仯WT���T��t���bk��Z������SE��-��<Tl��I����不�L�c7O��үG��{�!��s�а�O�����不�L�c7w���d�(��^z*��9�q�T��O�����不�L�[��y���/��c|0=�c���y���'�GQO��S>�α���|�~�}�~����2pt���!^#�}�����;�tv����?}d�������M��v:����1m�}�<�k�w�~�����ԎisK?7�Z��گ��2�    ���1m�}�<�k�wh�ޯ����Ԏis���]k�]���^��S;&�-��<�k��i�^����1q�y�<�k�'��v:����1m�y�<�k��J�[�N���S;��-��<�k�w�~=;�KO�6�>n�ڵ��j����不v|�y���C��~�}�t|/=�c����{>��O������s������S��~�}��t_KO�6��s�Ю��_j��>����1m�}�<�k���G�N���Ԏ�s���]k?�>�_���ai6��s�5�����>���l���ۻ���#s����tS<��x��/��xL�c 7O�/���2�?R=��-�<�k<5`�f���|L�c 7O�X�2�@R?��1���~����o���L�[�y��i�^��
�2��}�<l��|=B�Ǘ�SA&ѱ������^u/�>���2�n	��a#�M�u_JO�H�@n�"6^5`MQ���T��?�y���{H�Q������t�����#��!EG_JO�N�@n�:6�R�����	uK@7!���CƧ�SI��1��������)u|+=�dZ�yJ��{H�8>��R2�t���!e���+b��R��Ngyo����翇��b������T�iu��f#�E�^bƗ�SM��-�<�l<5`�%f|)=�dZ�y��xh�����j2����<�l�5`�%f����L�[�y���i�^���b2��}�<�l��|EL_JO1�V�>n�b��{�ԣ���K�&�ꖀnj6�4`SǗ�SM��1������W�����T��?�y���{HSǗ�SM��1����s����1u|)=�dZ�y���{HSǗ�SM��-�<�l�kLo�JO5�V�@n�j6�R����T��u��f#��!�/`k��,]��y���'���:����Y���wx��_>0w�S�Գ���K�&����S�F���Č/���L�[�y��xj��K��Rz�ɴ:r�T��Ѐ��!�S�&����S�F�]�^bƷ�SM&�-�<�l�4`��tI1�X�>n�b6�I�"��/���L�c7O1[��b������T�iuK@75o����K�&����S�F���a��Rz������<�l�=����K�&������9G@�C��:���j2����<�l�=����K�&�ꖀnj6�5����O���L�c 7O5u)b��Vz���:r�T�P������Xl�.��<�l��|EL_Jw��,��s<}d�������6��/���L�c 7O5/��3�CRM��-�<�l<5`�%f����L�c 7O5��2>��j2����<�l�5`�%f|+=�db���C�F�M��Mא��u���)f���+b��Rz�ɴ:�q����+�^ژ:���j2�n	��f#�M�0u|)=�dZ�y��xՀ=L_JO5���0��������1u|)=�dZ��{>��{HSǗ�SM��1��������1u|)=�dZ���C�F@�������T�iu��f#��!EL�JO5�X�@n�j6�R������ҥ�����~�����[�������������翇����6�����@�����S�F���Č���@�V�t�P��Ԁ����t�sZ�y��xh�����qN�c 7O5wX|���!.�2�-�<�l�4`��tq}8'ֱ�����~�������:�q����+�>�1u|3�Z氺%������7�����t�sZ�y��xՀ=L_Mw�����a@75u)b��l��9�����=�s�=�����.�:r�T�P��"����@�V�t�P��1�=d|:��V�@n�j6�R����t�sb�y���{H���C,6K�~nb6�I�"����;�t���_�c���=�=L}VL�~��p����L�c 7O5/��3>��j2�n	��f#�{/1���&����S�F�C�~��/���L�c 7O5w�{��PO5�X�t�P�pӀ�~�5$�db��y���'���:���b2��}�<�l�����o��N��T�iuK@75o����+�&����S�F���a�1>��j���a@75u�a�1���j2�����=�s�=������z�ɴ:r�T�P����K�&�ꖀnj6�5����[�&����S�F@�Cz�z�����L�c 7O5u)~[{��f`����C�F?����c|K�a���79���=�=L�*��ʘz�o���L�c 7O5/��3�CRM��-�<�l<5`�%f|K=�dZ�y��xh����1�T�iu��f#�{/1�k�&떀nj6n��o����L�c7O1�$_S��-��iu���)fk�WL��1��RO5�V�t�P��k�z�o���L�c 7O5�����[�&�t�P�P��"��o���L�c 7w��u)b���z�ɴ:r�T�P��"��o���L�[�y���ט�2>��j2����<�l�=����k�&���S�F@�C�_��b�X��s���O�1u|K�a����^����{�{�zSL}ic���z�ɴ:r�T��{/1�[�&�ꖀnj6���3�CRM��1�������,���!�&����S�F�]_b�{H��ĺ%���������SO1�X�>n�b6�I�"��o���L�c7O1[��b�KSǷ�SM��-�<�l�i���o���L�c 7O5�����[�&�t�P�P��"��o���L�c 7w��u)b���z�ɴ:r�T�P��"��o���L�[�y���ט�2>��j2����<�l�=����k�&���S�F@�Cz_��1����K?71�$_SǷ�v:�;�>��翇�/����a���z�ɤ:�q����"�z�0�K�%S�~nZ6��ү�3>��R2��}�<�l�;�_���tI%�N�>n�J6��ү��2>��B2�n���!d��&�z��H��d:�q󔱑�^QQ�G�SE&�1�������+j��:���*2�n���b��M��u|B=Ed��y���w�~=B_PO��󰟛���~�}u|@=%d���{>��'�GP���SA�ϱ�����~�}�t|>=d�����C�F?}�m���S����S�F?�>�z:>���1u�}�<�k������km���K>7�����t|:�?�s�����w�cs��w�T�w���*t�t|9���6�>n�ڵ��h�����p���asK?7�Z��گ��2����pN�c7O�Z�گ��c|7���6�>n�ڵ�۵_��e|7��Y�������v��6���7]>\Ήs���)]k>�W����t��s��yJג�N����O��>�6��s�Ю��M���t|1���6�>n�ڵ��j������>���󰟛�v��t�(���^���9m�}��=�s���G�N���]�is���]k?�>�v:����,s�����C��~x�m�k��9�q�Ԯ��nE;_Kw}8'α���v��t�(~�Zۇ�e����C��|R�h��K��6�ݽ���G������x���t|)=�c��y��xр�������1un	��^#��/0�$�c��y��xh�⯐���1}���<�k�5`�f|+=d
���C�F�M��MW��It���)`���+��Rz*�$:�q�T��߫��mC_JO�F�t�а�{�:���"2����<El�j����/���|�y��C�F@�C��:���22�����=�s�=���K�#����S�F@�C��:���B2�n	��!d# ^cz{��Tz*ɔ:r�T�P��"��o���L�c 7O)u�}ǧ�SJ��.��<�l��|EL_Jw��,�����|d���GSǗ�SM��1�������{�_JO5�V�t�P��Ԁ�����T�iu��f#���C�{H�ɴ:r�T�p׀ŗ���j2�n	��f#�{��kH���:�q���O�1u|)=�dZ��y����S�2���K�&�ꖀnj6�4`S���T� t  iu��f#�U�0�_JO5���0���������0�_JO5�V�@n��9����s|)=�dZ�y���{HS���T�iuK@75�S�C���T�iu��f#��!=L=Ƿ�SM&�1�����������=�b3�t���!f����a�9����Y�����翇��b�Y��s|)=�dZ�y��xр�����T�iuK@75O�{�_JO5�V�@n�j6��;d|*=�dZ�y���k��K��Vz��ĺ%�����������!)&����S�F?����s|)=�dZ��y����S�6��/���L�[�y��xӀ=L_JO5�V�@n�j6^5`SǗ�SM��<��f#��!EL_JO5�V�@n��9�R����T�iu��f#��!EL_JO5�V�t�P��1�=d|*=�dZ�y���{HSǷ�SM&�1�����������=�b3�t���!f���+b��R��Ngyo<d�������6��/���L�c 7O5/��3�CRM��-�<�l<5`�%f����L�c 7O5��2>��j2����<�l�5`�%f|+=�db���C�F�M��Mא��u���)f���+b��Rz�ɴ:�q����+�^ژ:���j2�n	��f#�M�0u|)=�dZ�y��xՀ=L_JO5���0��������1u|)=�dZ��{>��{HSǗ�SM��1��������1u|)=�dZ���C�F@�������T�iu��f#��!EL�JO5�X�@n�j6�R������ҥ�����~�����[�;��}_��G����a�E1�����c�&����S�F���Č����L�[�y��xj��K��\z�ɴ:r�T��Ѐ��!�=$�dZ�y���k��K�tI5�X�t�P�pӀ�~�5$�db��y���'���:>��b2��}�<�l�����mL�LO5�V�t�P��{�:>��j2����<�l�j�������|�y��C�F@�C��:>��j2�����=�s�=�����&����S�F@�C��:>��j2�n	��f# ^cz{��tz�ɴ:r�T�P��"������L�c 7O5u)~[{��f`����C�F?��GL�կ���N      �   /  x����j1�O��
���$�I�ɝ͂P,Tۻ�X���]}�f�B/J��L?C>B��y�_��lqg�����ӌ6�Ua��ݶۯ�9�^7��Y�^
�[-�u>c�8�}Hs3��B�[�A���er ��ʋ �!�Є!6�k9yu@��ZHu�H�0�SJ�a8�L/D��R��0��DUP��-rO�?��uT�%���zx�X��RC��T>R��x�JE�b�̫j�"i�yTh�n�O�r���"��.I�롆��#�F�QNT���F�%([v�(HJ@�f^������mj����͕0�      1   
   x���          2   
   x���          �   �  x���I��J���+\tr��� ��Bp@dqsS`1O�௿xN���=��J}o�o�UscXY��j`�/����W|���R��YT�i�����e7q�^\�ޘ�_��G
���'~����j�+��w/Ox�򋡃�^�$<J��P��2��#w����@Ǘ}�{���!����^?[ش34�\��Qw�m��7�����T�}�]2:�A����f݊N��À��T��]|˗�u�e38�%�D��2����Lӧ���{�'�.f)]���{���w��/��s�U�Jۋw	�ָ��έ���W��YL��A�օ�2^p����ӷ��4�I���y�F���T����NW�$@��c�
fz�3R���t��D�զv^�)L���"�(�wA�&�
��t��K�+a�����F���Ȭ�{H������D��|��oa�L�7ѱ�hG��3<F��P�ߐ�=�0����kk9Q�9Wa�zn�u�^#��A����^�q��	�G�-�6����]�(GE_���t��l�e��r�f�R`���X�>�/��t����I4G�,�9n��Ī|�I;�,9����L��湪$�3�~tO���5n��}�G/鲪AƱ3�eɗ�
o:k�ni2���`��%�0}��{�j�S�[�U}���T�q�6���QOi���Ң����f����4!1
ϓC9��N�諣c�8U4&�Ms���G}N�����#ೣ�Q|�d\�Zg���,=p~8`�Ixmp�}���j�3����v�"G�X�0y[�����g��_َ��wiק�`jne�	�5S�}2�Rқ+&�IH���;!_�$��s3LU����3�:��3.Z��r�]�:�zoU9ܒܚ vGi��K�2���53�|f�I��e���ƮHEKaV�=8��.�x���VPu*�ه�u��P�׬�A����Ǐ� ����      �   ?  x����J1�}�"��0�s�u\�
�`�[)���Z���;ӡ�qc�&N��s��b��Vb�X݊Ϗ�����KLv�������i}��_������m�T<����V��J���,����)HVD*J(�z�C�������@]KS\�O�G�t%d�K{�T�"S�E뤲���غ��@Y3�.vgq�!W�!�r�i=.+PՌ���/s�.7��b�9�ȧ9�8�W�.S�=�ќ�%i�_��X��]�S�%*�P��uWB_JM�?QM���%��i��-
�
�\���O)T�.V-k4��IZ�      �   f   x���v
Q���W(-N-�/�/�,���S��L�Q�L����E�)�
a�>���
F:
�:
���\��a��`��`dnI�!�@(6�DGh���)�.. �R�      5   
   x���          6   
   x���          7   
   x���          8   
   x���          3   
   x���          4   
   x���         